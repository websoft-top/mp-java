package com.gxwebsoft.shop.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单
 *
 * @author 科技小王子
 * @since 2025-01-11 10:45:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ShopOrder对象", description = "订单")
public class ShopOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单号")
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "订单类型，0商城订单 1预定订单/外卖 2会员卡")
    private Integer type;

    @ApiModelProperty(value = "快递/自提")
    private Integer deliveryType;

    @ApiModelProperty(value = "下单渠道，0小程序预定 1俱乐部训练场 3活动订场")
    private Integer channel;

    @ApiModelProperty(value = "微信支付订单号")
    private String transactionId;

    @ApiModelProperty(value = "微信退款订单号")
    private String refundOrder;

    @ApiModelProperty(value = "商户ID")
    private Long merchantId;

    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    @ApiModelProperty(value = "商户编号")
    private String merchantCode;

    @ApiModelProperty(value = "使用的优惠券id")
    private Integer couponId;

    @ApiModelProperty(value = "使用的会员卡id")
    private String cardId;

    @ApiModelProperty(value = "关联管理员id")
    private Integer adminId;

    @ApiModelProperty(value = "核销管理员id")
    private Integer confirmId;

    @ApiModelProperty(value = "IC卡号")
    private String icCard;

    @ApiModelProperty(value = "收货地址")
    private String address;

    private String addressLat;

    private String addressLng;

    @ApiModelProperty(value = "自提店铺id")
    private Integer selfTakeMerchantId;

    @ApiModelProperty(value = "自提店铺")
    private String selfTakeMerchantName;

    @ApiModelProperty(value = "配送开始时间")
    private String sendStartTime;

    @ApiModelProperty(value = "配送结束时间")
    private String sendEndTime;

    @ApiModelProperty(value = "发货店铺id")
    private Integer expressMerchantId;

    @ApiModelProperty(value = "发货店铺")
    private String expressMerchantName;

    @ApiModelProperty(value = "订单总额")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "减少的金额，使用VIP会员折扣、优惠券抵扣、优惠券折扣后减去的价格")
    private BigDecimal reducePrice;

    @ApiModelProperty(value = "实际付款")
    private BigDecimal payPrice;

    @ApiModelProperty(value = "用于统计")
    private BigDecimal price;

    @ApiModelProperty(value = "价钱，用于积分赠送")
    private BigDecimal money;

    @ApiModelProperty(value = "退款金额")
    private BigDecimal refundMoney;

    @ApiModelProperty(value = "教练价格")
    private BigDecimal coachPrice;

    @ApiModelProperty(value = "购买数量")
    private Integer totalNum;

    @ApiModelProperty(value = "教练id")
    private Integer coachId;

    @ApiModelProperty(value = "来源ID，存商品ID")
    private Integer formId;

    @ApiModelProperty(value = "支付的用户id")
    private Integer payUserId;

    @ApiModelProperty(value = "0余额支付, 1微信支付，102微信Native，2会员卡支付，3支付宝，4现金，5POS机，6VIP月卡，7VIP年卡，8VIP次卡，9IC月卡，10IC年卡，11IC次卡，12免费，13VIP充值卡，14IC充值卡，15积分支付，16VIP季卡，17IC季卡，18代付")
    private Integer payType;

    @ApiModelProperty(value = "代付支付方式,0余额支付, 1微信支付，102微信Native，2会员卡支付，3支付宝，4现金，5POS机，6VIP月卡，7VIP年卡，8VIP次卡，9IC月卡，10IC年卡，11IC次卡，12免费，13VIP充值卡，14IC充值卡，15积分支付，16VIP季卡，17IC季卡，18代付")
    private Integer friendPayType;

    @ApiModelProperty(value = "0未付款，1已付款")
    private Boolean payStatus;

    @ApiModelProperty(value = "0未使用，1已完成，2已取消，3取消中，4退款申请中，5退款被拒绝，6退款成功，7客户端申请退款")
    private Integer orderStatus;

    @ApiModelProperty(value = "发货状态(10未发货 20已发货 30部分发货)")
    private Integer deliveryStatus;

    @ApiModelProperty(value = "发货时间")
    private Date deliveryTime;

    @ApiModelProperty(value = "优惠类型：0无、1抵扣优惠券、2折扣优惠券、3、VIP月卡、4VIP年卡，5VIP次卡、6VIP会员卡、7IC月卡、8IC年卡、9IC次卡、10IC会员卡、11免费订单、12VIP充值卡、13IC充值卡、14VIP季卡、15IC季卡")
    private Integer couponType;

    @ApiModelProperty(value = "优惠说明")
    private String couponDesc;

    @ApiModelProperty(value = "二维码地址，保存订单号，支付成功后才生成")
    private String qrcode;

    @ApiModelProperty(value = "vip月卡年卡、ic月卡年卡回退次数")
    private Integer returnNum;

    @ApiModelProperty(value = "vip充值回退金额")
    private BigDecimal returnMoney;

    @ApiModelProperty(value = "预约详情开始时间数组")
    private String startTime;

    @ApiModelProperty(value = "是否已开具发票：0未开发票，1已开发票，2不能开具发票")
    private Integer isInvoice;

    @ApiModelProperty(value = "发票流水号")
    private String invoiceNo;

    @ApiModelProperty(value = "支付时间")
    private Date payTime;

    @ApiModelProperty(value = "退款时间")
    private Date refundTime;

    @ApiModelProperty(value = "申请退款时间")
    private Date refundApplyTime;

    @ApiModelProperty(value = "过期时间")
    private Date expirationTime;

    @ApiModelProperty(value = "对账情况：0=未对账；1=已对账；3=已对账，金额对不上；4=未查询到该订单")
    private Integer checkBill;

    @ApiModelProperty(value = "订单是否已结算(0未结算 1已结算)")
    private Integer isSettled;

    @ApiModelProperty(value = "系统版本号 0当前版本 value=其他版本")
    private Integer version;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "头像")
    @TableField(exist = false)
    private String avatar;

    @ApiModelProperty(value = "昵称")
    @TableField(exist = false)
    private String nickname;

    @ApiModelProperty(value = "真实姓名")
    @TableField(exist = false)
    private String realName;

    @ApiModelProperty(value = "手机号码")
    @TableField(exist = false)
    private String phone;

    @ApiModelProperty(value = "手机号码(脱敏)")
    @TableField(exist = false)
    private String mobile;

    @ApiModelProperty(value = "备注")
    private String comments;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "自提码")
    private String selfTakeCode;

    @ApiModelProperty(value = "是否已收到赠品")
    private Boolean hasTakeGift;

    @ApiModelProperty(value = "accessToken")
    @TableField(exist = false)
    private String accessToken;

    @ApiModelProperty(value = "openid")
    @TableField(exist = false)
    private String openid;

    @ApiModelProperty(value = "订单商品")
    @TableField(exist = false)
    private List<ShopOrderGoods> orderGoods;

}
