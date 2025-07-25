package com.gxwebsoft.common.system.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gxwebsoft.common.core.annotation.QueryField;
import com.gxwebsoft.common.core.annotation.QueryType;
import com.gxwebsoft.common.core.web.BaseParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * 用户查询参数
 *
 * @author WebSoft
 * @since 2021-08-29 20:35:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "用户查询参数")
public class UserParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty("用户类型, 0普通用户 10企业用户")
    private Integer type;

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("用户编码")
    private String userCode;

    @ApiModelProperty("性别(字典)")
    @QueryField(type = QueryType.EQ)
    private String sex;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("邮箱是否验证, 0否, 1是")
    @QueryField(type = QueryType.EQ)
    private Integer emailVerified;

    @ApiModelProperty("别名")
    private String alias;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("出生日期")
    private String birthday;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty("可用余额")
    private BigDecimal balance;

    @ApiModelProperty("机构id")
    @QueryField(type = QueryType.EQ)
    private Integer organizationId;

    @ApiModelProperty("用户分组ID")
    @QueryField(type = QueryType.EQ)
    private Integer groupId;

    @ApiModelProperty("注册来源客户端")
    @QueryField(type = QueryType.EQ)
    private String platform;

    @ApiModelProperty("是否下线会员")
    private Integer offline;

    @ApiModelProperty("上级机构ID")
    @QueryField(type = QueryType.IN)
    private Integer parentId;

    @ApiModelProperty("状态, 0正常, 1冻结")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty("是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty("角色id")
    @TableField(exist = false)
    private Integer roleId;

    @ApiModelProperty("角色标识")
    @TableField(exist = false)
    private String roleCode;

    @ApiModelProperty(value = "所在省份")
    private String province;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "所在辖区")
    private String region;

    @ApiModelProperty("关注数")
    private Integer followers;

    @ApiModelProperty("粉丝数")
    private Integer fans;

    @ApiModelProperty("获赞数")
    private Integer likes;

    @ApiModelProperty("评论数")
    private Integer commentNumbers;

    @ApiModelProperty(value = "择偶区域")
    @TableField(exist = false)
    private String cityMate;

    @ApiModelProperty("机构名称")
    @TableField(exist = false)
    private String organizationName;

    @ApiModelProperty("公司名称")
    @TableField(exist = false)
    private String companyName;

    @ApiModelProperty("公司名称")
    private String customerName;

    @ApiModelProperty("性别名称")
    @TableField(exist = false)
    private String sexName;

    @ApiModelProperty("推荐状态")
    @TableField(exist = false)
    private Integer recommend;

    @ApiModelProperty("搜索关键字")
    @TableField(exist = false)
    private String keywords;

    @ApiModelProperty(value = "会员等级")
    @TableField(exist = false)
    private Integer gradeId;

    @ApiModelProperty("按角色搜索")
    @TableField(exist = false)
    private String roleIds;

    @ApiModelProperty("用户类型 sys系统用户 org机构职员 member商城会员 ")
    @TableField(exist = false)
    private String userType;

    @ApiModelProperty("支付宝授权码")
    @TableField(exist = false)
    private String authCode;

    @ApiModelProperty("微信凭证code")
    @TableField(exist = false)
    private String code;

    @ApiModelProperty("推荐人ID")
    @QueryField(type = QueryType.IN)
    private Integer refereeId;

    @ApiModelProperty("租户ID")
    private Integer tenantId;

    @ApiModelProperty("二维码类型")
    @TableField(exist = false)
    private String codeType;

    @ApiModelProperty("二维码内容 填网址扫码后可跳转")
    @TableField(exist = false)
    private String codeContent;

    @ApiModelProperty("是否内部职员")
    @TableField(exist = false)
    private Boolean isStaff;

    @ApiModelProperty(value = "是否管理员")
    @TableField(exist = false)
    private Boolean isAdmin;

    @ApiModelProperty("openid")
    private String openid;

    @ApiModelProperty("unionid")
    private String unionid;

    @ApiModelProperty("最后结算时间")
    @TableField(exist = false)
    private Date settlementTime;

    @ApiModelProperty("报餐时间")
    @TableField(exist = false)
    private String deliveryTime;

    @ApiModelProperty("用户ID集合")
    @TableField(exist = false)
    private Set<Integer> userIds;

    @ApiModelProperty("用户手机号码集合")
    @TableField(exist = false)
    private Set<String> phones;

    @ApiModelProperty("是否查询用户详细资料表")
    @TableField(exist = false)
    private Boolean showProfile;

    @ApiModelProperty("openId")
    @TableField(exist = false)
    private String openId;

    @ApiModelProperty(value = "可管理的商户")
    @QueryField(type = QueryType.LIKE)
    private String merchants;

    @ApiModelProperty(value = "商户ID")
    @QueryField(type = QueryType.EQ)
    private Long merchantId;

    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    @ApiModelProperty("关联用户ID")
    @TableField(exist = false)
    private Integer sysUserId;
}
