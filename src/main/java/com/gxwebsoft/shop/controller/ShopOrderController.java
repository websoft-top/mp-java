package com.gxwebsoft.shop.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.gxwebsoft.common.core.config.ConfigProperties;
import com.gxwebsoft.common.core.utils.RedisUtil;
import com.gxwebsoft.common.core.web.BaseController;
import com.gxwebsoft.common.system.entity.Payment;
import com.gxwebsoft.shop.service.ShopOrderService;
import com.gxwebsoft.shop.entity.ShopOrder;
import com.gxwebsoft.shop.param.ShopOrderParam;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.core.web.PageResult;
import com.gxwebsoft.common.core.web.BatchParam;
import com.gxwebsoft.common.system.entity.User;
import com.wechat.pay.java.core.notification.NotificationConfig;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.core.notification.RSANotificationConfig;
import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.partnerpayments.jsapi.model.Transaction;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 订单控制器
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Api(tags = "订单管理")
@RestController
@RequestMapping("/api/shop/shop-order")
public class ShopOrderController extends BaseController {
  private static final Logger logger = LoggerFactory.getLogger(ShopOrderController.class);
  @Resource
  private ShopOrderService shopOrderService;
  @Resource
  private RedisUtil redisUtil;
  @Resource
  private ConfigProperties conf;
  @Value("${spring.profiles.active}")
  String active;

  @ApiOperation("分页查询订单")
  @GetMapping("/page")
  public ApiResult<PageResult<ShopOrder>> page(ShopOrderParam param) {
    // 使用关联查询
    return success(shopOrderService.pageRel(param));
  }

  @ApiOperation("查询全部订单")
  @GetMapping()
  public ApiResult<List<ShopOrder>> list(ShopOrderParam param) {
    // 使用关联查询
    return success(shopOrderService.listRel(param));
  }

  @PreAuthorize("hasAuthority('shop:shopOrder:list')")
  @ApiOperation("根据id查询订单")
  @GetMapping("/{id}")
  public ApiResult<ShopOrder> get(@PathVariable("id") Integer id) {
    // 使用关联查询
    return success(shopOrderService.getByIdRel(id));
  }

  @ApiOperation("添加订单")
  @PostMapping()
  public ApiResult<?> save(@RequestBody ShopOrder shopOrder) {
    // 记录当前登录用户id
    User loginUser = getLoginUser();
    if (loginUser != null) {
      shopOrder.setUserId(loginUser.getUserId());
      shopOrder.setOpenid(loginUser.getOpenid());
      shopOrder.setPayUserId(loginUser.getUserId());
      if (shopOrder.getOrderNo() == null) {
        shopOrder.setOrderNo(Long.toString(IdUtil.getSnowflakeNextId()));
      }
      if (shopOrder.getComments() == null) {
        shopOrder.setComments("暂无");
      }
      // 微信支付(商品金额不能为0)
      if (shopOrder.getTotalPrice().compareTo(BigDecimal.ZERO) == 0) {
        return fail("商品金额不能为0");
      }
      // 百色中学项目捐赠金额不能低于20元
      if (shopOrder.getTenantId().equals(10324) && shopOrder.getTotalPrice().compareTo(new BigDecimal("10")) < 0) {
        return fail("捐款金额最低不能少于10元，感谢您的爱心捐赠^_^");
      }
      // 测试支付
      if (loginUser.getUserId().equals(28748)) {
        shopOrder.setPrice(new BigDecimal("0.01"));
        shopOrder.setTotalPrice(new BigDecimal("0.01"));
      }
      if (shopOrderService.save(shopOrder)) {
        return success("下单成功", shopOrderService.createWxOrder(shopOrder));
      }
    }
    return fail("添加失败");
  }

  @PreAuthorize("hasAuthority('shop:shopOrder:update')")
  @ApiOperation("修改订单")
  @PutMapping()
  public ApiResult<?> update(@RequestBody ShopOrder shopOrder) {
    if (shopOrderService.updateById(shopOrder)) {
      return success("修改成功");
    }
    return fail("修改失败");
  }

  @ApiOperation("删除订单")
  @DeleteMapping("/{id}")
  public ApiResult<?> remove(@PathVariable("id") Integer id) {
    if (shopOrderService.removeById(id)) {
      return success("删除成功");
    }
    return fail("删除失败");
  }

  @ApiOperation("批量添加订单")
  @PostMapping("/batch")
  public ApiResult<?> saveBatch(@RequestBody List<ShopOrder> list) {
    if (shopOrderService.saveBatch(list)) {
      return success("添加成功");
    }
    return fail("添加失败");
  }

  @ApiOperation("批量修改订单")
  @PutMapping("/batch")
  public ApiResult<?> removeBatch(@RequestBody BatchParam<ShopOrder> batchParam) {
    if (batchParam.update(shopOrderService, "order_id")) {
      return success("修改成功");
    }
    return fail("修改失败");
  }

  @ApiOperation("批量删除订单")
  @DeleteMapping("/batch")
  public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
    if (shopOrderService.removeByIds(ids)) {
      return success("删除成功");
    }
    return fail("删除失败");
  }

  @ApiOperation("修复订单")
  @PutMapping("/repair")
  public ApiResult<?> repair(@RequestBody ShopOrder shopOrder) {
    final ShopOrder order = shopOrderService.getByOutTradeNo(shopOrder.getOrderNo());
    if(order != null){
      shopOrderService.queryOrderByOutTradeNo(order);
      return success("修复成功");
    }
    return fail("修复失败");
  }

  @ApiModelProperty("异步通知")
  @PostMapping("/notify/{tenantId}")
  public String wxNotify(@RequestHeader Map<String, String> header, @RequestBody String body, @PathVariable("tenantId") Integer tenantId) {
    logger.info("异步通知*************** = " + tenantId);

    // 获取支付配置信息用于解密
    String key = "Payment:1:".concat(tenantId.toString());
    Payment payment = redisUtil.get(key, Payment.class);
    String uploadPath = conf.getUploadPath();

    // 开发环境默认配置 - 请根据实际环境修改
    String apiV3Key = System.getProperty("wechat.pay.apiV3Key", "");
    String privateKey = System.getProperty("wechat.pay.privateKey", "");
    String apiclientCert = System.getProperty("wechat.pay.apiclientCert", "");
    String pubKey = System.getProperty("wechat.pay.pubKey", "");

    // 生产环境
    if (ObjectUtil.isNotEmpty(payment)) {
      // 检查 payment 字段是否为空，并避免直接解析为数字
      apiV3Key = payment.getApiKey();
      privateKey = payment.getApiclientKey();
      apiclientCert = conf.getUploadPath().concat("/file").concat(payment.getApiclientCert());
      pubKey = uploadPath.concat("file").concat(payment.getPubKey());
    }

    RequestParam requestParam = new RequestParam.Builder()
      .serialNumber(header.get("wechatpay-serial"))
      .nonce(header.get("wechatpay-nonce"))
      .signature(header.get("wechatpay-signature"))
      .timestamp(header.get("wechatpay-timestamp"))
      .body(body)
      .build();

    // 如果已经初始化了 RSAAutoCertificateConfig，可直接使用
    // 没有的话，则构造一个
    NotificationConfig config = new RSANotificationConfig.Builder()
      .apiV3Key(apiV3Key)
      .certificatesFromPath(apiclientCert)
      .build();

    // 初始化 NotificationParser
    NotificationParser parser = new NotificationParser(config);

    // 以支付通知回调为例，验签、解密并转换成 Transaction
    try {
      Transaction transaction = parser.parse(requestParam, Transaction.class);
      if (StrUtil.equals("支付成功", transaction.getTradeStateDesc())) {
        final String outTradeNo = transaction.getOutTradeNo();
        final String transactionId = transaction.getTransactionId();
        final Integer total = transaction.getAmount().getTotal();
        final String tradeStateDesc = transaction.getTradeStateDesc();
        final Transaction.TradeStateEnum tradeState = transaction.getTradeState();
        final Transaction.TradeTypeEnum tradeType = transaction.getTradeType();
        System.out.println("transaction = " + transaction);
        System.out.println("tradeStateDesc = " + tradeStateDesc);
        System.out.println("tradeType = " + tradeType);
        System.out.println("tradeState = " + tradeState);
        System.out.println("outTradeNo = " + outTradeNo);
        System.out.println("amount = " + total);
        // 1. 查询要处理的订单
        ShopOrder order = shopOrderService.getByOutTradeNo(outTradeNo);
        logger.info("order = " + order);
        // 2. 已支付则跳过
        if (order.getPayStatus().equals(true)) {
          return "SUCCESS";
        }
        // 2. 未支付则处理更新订单状态
        if (order.getPayStatus().equals(false)) {
          // 5. TODO 处理订单状态
          order.setPayTime(DateUtil.date());
          order.setExpirationTime(order.getCreateTime());
          order.setPayStatus(true);
          order.setTransactionId(transactionId);
          order.setPayPrice(new BigDecimal(NumberUtil.decimalFormat("0.00", total * 0.01)));
          order.setExpirationTime(DateUtil.offset(DateUtil.date(), DateField.YEAR, 10));
          System.out.println("实际付款金额 = " + order.getPayPrice());
          shopOrderService.updateByOutTradeNo(order);
          return "SUCCESS";
        }
      }
    } catch (Exception $e) {
      System.out.println($e.getMessage());
    }

    return "fail";
  }

}
