package com.gxwebsoft.common.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户
 *
 * @author WebSoft
 * @since 2018-12-24 16:10:13
 */
@Data
@ApiModel(description = "用户")
@TableName("sys_user")
public class UserInfo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @TableId(type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty("用户类型, 0普通用户 6开发者 10企业用户")
    private Integer type;

    @ApiModelProperty("用户编码")
    private String userCode;

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("头像")
    private String bgImage;

    @ApiModelProperty("性别, 字典标识")
    private String sex;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("职务")
    private String position;

    @ApiModelProperty("邮箱是否验证, 0否, 1是")
    private Integer emailVerified;

    @ApiModelProperty("别名")
    private String alias;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("出生日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty("所在国家")
    private String country;

    @ApiModelProperty(value = "所在省份")
    private String province;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "所在辖区")
    private String region;

    @ApiModelProperty("街道地址")
    private String address;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty("用户可用余额")
    private BigDecimal balance;

    @ApiModelProperty("用户可用积分")
    private Integer points;

    @ApiModelProperty("用户总支付的金额")
    private String payMoney;

    @ApiModelProperty("实际消费的金额(不含退款)")
    private String expendMoney;

    @ApiModelProperty("会员等级ID")
    private Integer gradeId;

    @ApiModelProperty("个人简介")
    private String introduction;

    @ApiModelProperty("机构ID")
    private Integer organizationId;

    @ApiModelProperty("客户ID")
    private Integer customerId;

    @ApiModelProperty("企业ID")
    private Integer companyId;

    @ApiModelProperty("注册来源客户端")
    private String platform;

    @ApiModelProperty("兴趣爱好")
    private String interest;

    @ApiModelProperty("身高")
    private String height;

    @ApiModelProperty("体重")
    private String weight;

    @ApiModelProperty("学历")
    private String education;

    @ApiModelProperty("月薪")
    private String monthlyPay;

    @ApiModelProperty("是否下线会员")
    private Integer offline;

    @ApiModelProperty("关注数")
    private Integer followers;

    @ApiModelProperty("粉丝数")
    private Integer fans;

    @ApiModelProperty("获赞数")
    private Integer likes;

    @ApiModelProperty("评论数")
    private Integer commentNumbers;

    @ApiModelProperty("是否推荐")
    private Integer recommend;

    @ApiModelProperty("备注")
    private String comments;

    @ApiModelProperty("状态, 0正常, 1冻结")
    private Integer status;

    @ApiModelProperty("是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "租户id")
    private Integer tenantId;

    @ApiModelProperty(value = "租户名称")
    @TableField(exist = false)
    private String tenantName;

    @ApiModelProperty(value = "最后结算时间")
    private Date settlementTime;

    @ApiModelProperty("注册时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("是否已实名认证")
    private Integer certification;

    @ApiModelProperty("机构名称")
    @TableField(exist = false)
    private String organizationName;

    @ApiModelProperty("性别名称")
    @TableField(exist = false)
    private String sexName;

    @ApiModelProperty("会员等级")
    @TableField(exist = false)
    private String gradeName;

    @ApiModelProperty("默认注册的角色ID")
    @TableField(exist = false)
    private Integer roleId;

    @ApiModelProperty("角色列表")
    @TableField(exist = false)
    private List<Object> roles;

    @ApiModelProperty("权限列表")
    @TableField(exist = false)
    private List<Menu> authorities;

    @ApiModelProperty("微信凭证")
    @TableField(exist = false)
    private String code;

    @ApiModelProperty("推荐人ID")
    @TableField(exist = false)
    private Integer dealerId;

    @ApiModelProperty("微信openid")
    @TableField(exist = false)
    private String openid;

    @ApiModelProperty("微信unionid")
    @TableField(exist = false)
    private String unionid;

    @ApiModelProperty("所属商户名称")
    @TableField(exist = false)
    private String merchantName;

    @ApiModelProperty("ico文件")
    @TableField(exist = false)
    private String logo;

    @ApiModelProperty("创建的应用数量")
    @TableField(exist = false)
    private Double apps;

    @ApiModelProperty("租户设置信息")
    @TableField(exist = false)
    private String setting;

    @ApiModelProperty("手机号(脱敏)")
    @TableField(exist = false)
    private String mobile;

}
