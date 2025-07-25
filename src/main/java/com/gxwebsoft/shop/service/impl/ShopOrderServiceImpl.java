package com.gxwebsoft.shop.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxwebsoft.common.core.config.ConfigProperties;
import com.gxwebsoft.common.core.exception.BusinessException;
import com.gxwebsoft.common.core.utils.RedisUtil;
import com.gxwebsoft.common.system.entity.Payment;
import com.gxwebsoft.shop.entity.ShopOrderGoods;
import com.gxwebsoft.shop.mapper.ShopOrderMapper;
import com.gxwebsoft.shop.service.ShopOrderGoodsService;
import com.gxwebsoft.shop.service.ShopOrderService;
import com.gxwebsoft.shop.entity.ShopOrder;
import com.gxwebsoft.shop.param.ShopOrderParam;
import com.gxwebsoft.common.core.web.PageParam;
import com.gxwebsoft.common.core.web.PageResult;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAConfig;
import com.wechat.pay.java.core.RSAPublicKeyConfig;
import com.wechat.pay.java.core.exception.ServiceException;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.jsapi.model.*;
import com.wechat.pay.java.service.payments.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 订单Service实现
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Service
public class ShopOrderServiceImpl extends ServiceImpl<ShopOrderMapper, ShopOrder> implements ShopOrderService {
    @Value("${spring.profiles.active}")
    String active;
    @Resource
    private ConfigProperties config;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private ShopOrderGoodsService shopOrderGoodsService;

    // 开发环境证书路径 - 请根据实际环境修改
    public static String privateKeyPath = System.getProperty("wechat.dev.privateKeyPath", "");
    public static String privateCertPath = System.getProperty("wechat.dev.privateCertPath", "");
    public static String wechatpayCertPath = System.getProperty("wechat.dev.wechatpayCertPath", "");  // 平台证书

    @Override
    public PageResult<ShopOrder> pageRel(ShopOrderParam param) {
        PageParam<ShopOrder, ShopOrderParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number asc, create_time desc");
        List<ShopOrder> list = baseMapper.selectPageRel(page, param);

        // 整理订单数据
        if (!CollectionUtils.isEmpty(list)) {
          final Set<Integer> orderIds = list.stream().map(ShopOrder::getOrderId).collect(Collectors.toSet());
          final List<ShopOrderGoods> goodsList = shopOrderGoodsService.list(new LambdaQueryWrapper<ShopOrderGoods>().in(ShopOrderGoods::getOrderId,orderIds));
          final Map<Integer, List<ShopOrderGoods>> collect = goodsList.stream().collect(Collectors.groupingBy(ShopOrderGoods::getOrderId));
          list.forEach(d -> {
            d.setOrderGoods(collect.get(d.getOrderId()));
          });
        }
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<ShopOrder> listRel(ShopOrderParam param) {
        List<ShopOrder> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<ShopOrder, ShopOrderParam> page = new PageParam<>();
        page.setDefaultOrder("sort_number asc, create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public ShopOrder getByIdRel(Integer orderId) {
        ShopOrderParam param = new ShopOrderParam();
        param.setOrderId(orderId);
        return param.getOne(baseMapper.selectListRel(param));
    }

    @Override
    public HashMap<String, String> createWxOrder(ShopOrder order) {
        // 后台微信支付配置信息
        final Payment payment = getPayment(order);
        // 微信小程序信息
        final JSONObject wxConfig = getWxConfig(order);
        // 返回的订单数据
        final HashMap<String, String> orderInfo = new HashMap<>();
        // 构建service
        JsapiServiceExtension service = getWxService(order);

        // 订单金额
        BigDecimal decimal = order.getTotalPrice();
        final BigDecimal multiply = decimal.multiply(new BigDecimal(100));
        Integer money = multiply.intValue();

        PrepayRequest request = new PrepayRequest();
        Amount amount = new Amount();
        amount.setTotal(money);
        amount.setCurrency("CNY");
        request.setAmount(amount);
        request.setAppid(wxConfig.getString("appId"));
        request.setMchid(payment.getMchId());
        request.setDescription(order.getComments());
        request.setOutTradeNo(order.getOrderNo());
        request.setAttach(order.getTenantId().toString());
        final Payer payer = new Payer();
        payer.setOpenid(order.getOpenid());
        request.setPayer(payer);
        request.setNotifyUrl("https://server.gxwebsoft.com/api/system/wx-pay/notify/" + order.getTenantId()); // 默认回调地址
        // 测试环境
        if (active.equals("dev")) {
            amount.setTotal(1);
            request.setAmount(amount);
            request.setNotifyUrl("http://jimei-api.natapp1.cc/api/shop/wx-pay/notify/" + order.getTenantId()); // 默认回调地址
        }
        // 后台配置的回调地址
        if (StrUtil.isNotBlank(payment.getNotifyUrl())) {
          request.setNotifyUrl(payment.getNotifyUrl().concat("/").concat(order.getTenantId().toString()));
        }
        PrepayWithRequestPaymentResponse response = service.prepayWithRequestPayment(request);
        orderInfo.put("provider", "wxpay");
        orderInfo.put("timeStamp", response.getTimeStamp());
        orderInfo.put("nonceStr", response.getNonceStr());
        orderInfo.put("package", response.getPackageVal());
        orderInfo.put("signType", "RSA");
        orderInfo.put("paySign", response.getPaySign());
        orderInfo.put("orderNo", order.getOrderNo());
        return orderInfo;
    }

    @Override
    public ShopOrder getByOutTradeNo(String outTradeNo) {
      return baseMapper.getByOutTradeNo(outTradeNo);
    }

    /**
     * 修复订单支付状态
     * @param shopOrder
     */
    @Override
    public Boolean queryOrderByOutTradeNo(ShopOrder shopOrder) {
      // 后台微信支付配置信息
      final Payment payment = getPayment(shopOrder);
      QueryOrderByOutTradeNoRequest queryRequest = new QueryOrderByOutTradeNoRequest();
      queryRequest.setMchid(payment.getMchId());
      queryRequest.setOutTradeNo(shopOrder.getOrderNo());
      // 构建service
      JsapiServiceExtension service = getWxService(shopOrder);
      try {
        Transaction result = service.queryOrderByOutTradeNo(queryRequest);
        if(result.getTradeState().equals(Transaction.TradeStateEnum.SUCCESS)){
          shopOrder.setPayStatus(true);
          shopOrder.setPayTime(DateUtil.date());
          shopOrder.setTransactionId(result.getTransactionId());
          updateById(shopOrder);
          return true;
        }
      } catch (ServiceException e) {
        // API返回失败, 例如ORDER_NOT_EXISTS
        System.out.printf("code=[%s], message=[%s]\n", e.getErrorCode(), e.getErrorMessage());
        System.out.printf("reponse body=[%s]\n", e.getResponseBody());
      }
      return false;
    }

  @Override
  public void updateByOutTradeNo(ShopOrder order) {
    baseMapper.updateByOutTradeNo(order);
  }

  public Payment getPayment(ShopOrder order){
      String key2 = "Payment:".concat(order.getPayType().toString()).concat(":").concat(order.getTenantId().toString());
      final Payment payment = redisUtil.get(key2, Payment.class);
      if(ObjectUtil.isNotEmpty(payment)){
        return payment;
      };
      return null;
    };

    public JSONObject getWxConfig(ShopOrder order){
      // 微信小程序(微信支付)
      String key = "mp-weixin:".concat(order.getTenantId().toString());
      final String string = redisUtil.get(key);
      return JSONObject.parseObject(string);
    }

    public JsapiServiceExtension getWxService(ShopOrder order){
      Integer payType = order.getPayType();
      final String uploadPath = config.getUploadPath();   // 服务器本地路径
      final HashMap<String, String> orderInfo = new HashMap<>();
      // 微信小程序(微信支付)
      String key = "mp-weixin:".concat(order.getTenantId().toString());
      final String string = redisUtil.get(key);
//    System.out.println("string = " + string);
      final JSONObject mpWx = JSONObject.parseObject(string);
//    System.out.println("mpWx = " + mpWx);
      String key2 = "Payment:".concat(payType.toString()).concat(":").concat(order.getTenantId().toString());
      final Payment payment = redisUtil.get(key2, Payment.class);
      if (ObjectUtil.isEmpty(payment)) {
        throw new BusinessException("支付配置信息为空");
      }

      String privateKey = uploadPath.concat("/file").concat(payment.getApiclientKey());    // 秘钥证书
      String apiclientCert = uploadPath.concat("/file").concat(payment.getApiclientCert());
      String pubKey = uploadPath.concat("/file").concat(payment.getPubKey());   // 公钥证书
      // 开发环境配置
      if (active.equals("dev")) {
        privateKey = privateKeyPath;
        apiclientCert = wechatpayCertPath;
      }

      // 兼容公钥
      Config config;
      if (payment.getPubKey() != null && !payment.getPubKey().isEmpty()) {
        config = new RSAPublicKeyConfig.Builder()
          .merchantId(payment.getMchId())
          .privateKeyFromPath(privateKey)
          .publicKeyFromPath(pubKey)
          .publicKeyId(payment.getPubKeyId())
          .merchantSerialNumber(payment.getMerchantSerialNumber())
          .apiV3Key(payment.getApiKey())
          .build();
      } else {
        config = new RSAConfig.Builder()
          .merchantId(payment.getMchId())
          .privateKeyFromPath(privateKey)
          .merchantSerialNumber(payment.getMerchantSerialNumber())
          .wechatPayCertificatesFromPath(apiclientCert)
          .build();
      }

      // 构建service
      return new JsapiServiceExtension.Builder().config(config).build();
    }

}
