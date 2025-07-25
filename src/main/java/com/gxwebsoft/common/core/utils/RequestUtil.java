package com.gxwebsoft.common.core.utils;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONObject;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.system.entity.Payment;
import com.gxwebsoft.common.system.entity.User;
import com.gxwebsoft.common.system.entity.UserRole;
import com.gxwebsoft.shop.entity.ShopOrder;
import com.gxwebsoft.shop.entity.ShopMerchantAccount;
import com.wechat.pay.java.service.partnerpayments.jsapi.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class RequestUtil {
    private static final String host = "https://server.gxwebsoft.com/api";
    private static String ACCESS_TOKEN;
    private static String TENANT_ID;

    public void setTenantId(String tenantId) {
        TENANT_ID = tenantId;
    }

    public void setAccessToken(String token) {
        ACCESS_TOKEN = token;
    }

    // 预付请求付款(余额支付)
    public Object balancePay(ShopOrder order) {
        // 设置租户ID
        setTenantId(order.getTenantId().toString());
        // 设置token
        setAccessToken(order.getAddress());
        // 余额支付接口
        String path = "/system/payment/balancePay";
        try {
            // 链式构建请求
            final String body = HttpRequest.post(host.concat(path))
                    .header("Tenantid", TENANT_ID)
                    .header("Authorization", ACCESS_TOKEN)
                    .body(JSONUtil.toJSONString(order))//表单内容
                    .timeout(20000)//超时，毫秒
                    .execute().body();
            return JSONUtil.parseObject(body, ApiResult.class).getData();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 微信支付通知
    public String pushWxPayNotify(Transaction transaction, Payment payment) {
        // 设置租户ID
        setTenantId(payment.getTenantId().toString());
        // 推送支付通知地址
        String path = payment.getNotifyUrl();
        try {
            // 链式构建请求
            return HttpRequest.post(path)
                    .header("Tenantid", TENANT_ID)
                    .body(JSONUtil.toJSONString(transaction))//表单内容
                    .timeout(20000)//超时，毫秒
                    .execute().body();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "支付失败";
    }


    public User getUserByPhone(String phone) {
        String path = "/system/user/getByPhone/" + phone;
        try {
            // 链式构建请求
            String result = HttpRequest.get(host.concat(path))
                    .header("Authorization", ACCESS_TOKEN)
                    .header("Tenantid", TENANT_ID)
                    .timeout(20000)//超时，毫秒
                    .execute().body();

            JSONObject jsonObject = JSONObject.parseObject(result);
            final String data = jsonObject.getString("data");
            return JSONObject.parseObject(data, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getByUserId(Integer userId) {
        String path = "/system/user/" + userId;
        try {
            // 链式构建请求
            String result = HttpRequest.get(host.concat(path))
                    .header("Authorization", ACCESS_TOKEN)
                    .header("Tenantid", TENANT_ID)
                    .timeout(20000)//超时，毫秒
                    .execute().body();

            JSONObject jsonObject = JSONObject.parseObject(result);
            System.out.println("jsonObject = " + jsonObject);
            final String data = jsonObject.getString("data");
            return JSONObject.parseObject(data, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

  // 新增用户
    public boolean saveUserByPhone(ShopMerchantAccount merchantAccount) {
        String path = "/system/user/";
        try {
            HashMap<String, Object> map = new HashMap<>();
            map.put("nickname", merchantAccount.getRealName());
            map.put("username", merchantAccount.getPhone());
            map.put("realName", merchantAccount.getRealName());
            map.put("phone", merchantAccount.getPhone());
            map.put("merchantId", merchantAccount.getMerchantId());
            final ArrayList<Object> roles = new ArrayList<>();
            final UserRole userRole = new UserRole();
            userRole.setUserId(merchantAccount.getUserId());
            userRole.setRoleId(merchantAccount.getRoleId());
            userRole.setTenantId(merchantAccount.getTenantId());
            roles.add(userRole);
            map.put("roles", roles);
            map.put("tenantId", TENANT_ID);
            // 链式构建请求
            String result = HttpRequest.post(host.concat(path))
                    .header("Authorization", ACCESS_TOKEN)
                    .header("Tenantid", TENANT_ID)
                    .body(JSONUtil.toJSONString(map))//表单内容
                    .timeout(20000)//超时，毫秒
                    .execute().body();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public ApiResult<?> updateUserBalance(String path, User user) {
        try {
            // 链式构建请求
            final String body = HttpRequest.put(host.concat(path))
                    .header("Authorization", ACCESS_TOKEN)
                    .header("Tenantid", TENANT_ID)
                    .body(JSONUtil.toJSONString(user))
                    .timeout(20000)
                    .execute().body();
            return JSONUtil.parseObject(body, ApiResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getParent(Integer userId) {
        try {
            // 链式构建请求
            final String result = HttpRequest.get(host.concat("/system/user-referee/getReferee/" + userId))
                    .header("Authorization", ACCESS_TOKEN)
                    .header("Tenantid", TENANT_ID)
                    .timeout(20000)
                    .execute().body();
            JSONObject jsonObject = JSONObject.parseObject(result);
            final String data = jsonObject.getString("data");
            return JSONObject.parseObject(data, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 更新用户信息
    public void updateUser(User user) {
        String path = "/system/user/";
        try {
            // 链式构建请求
            final String body = HttpRequest.put(host.concat(path))
                    .header("Authorization", ACCESS_TOKEN)
                    .header("Tenantid", TENANT_ID)
                    .body(JSONUtil.toJSONString(user))
                    .timeout(20000)
                    .execute().body();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMpOrderQrCode(String orderNo) {
        String path = "/wx-login/getOrderQRCode/";
        try {
            // 链式构建请求
            final String body = HttpRequest.get(host.concat(path).concat(orderNo))
                    .header("Authorization", ACCESS_TOKEN)
                    .header("tenantId", TENANT_ID)
                    .timeout(20000)
                    .execute().body();
            final JSONObject jsonObject = JSONObject.parseObject(body);
            final String qrCode = jsonObject.getString("message");
            return qrCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getOrderQRCodeUnlimited(String orderNo) {
        String path = "/wx-login/getOrderQRCodeUnlimited/";
        try {
            // 链式构建请求
            final String body = HttpRequest.get(host.concat(path).concat(orderNo))
                    .header("Authorization", ACCESS_TOKEN)
                    .header("tenantId", TENANT_ID)
                    .timeout(20000)
                    .execute().body();
            System.out.println("body = " + body);
            final JSONObject jsonObject = JSONObject.parseObject(body);
            final String qrCode = jsonObject.getString("message");
            System.out.println("qrCode = " + qrCode);
            return qrCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateUserMerchantId(User user) {
        String path = "/system/user/updateUserMerchantId";
        try {
            // 链式构建请求
            final String body = HttpRequest.put(host.concat(path))
                    .header("Authorization", ACCESS_TOKEN)
                    .header("tenantId", TENANT_ID)
                    .body(JSONUtil.toJSONString(user))
                    .timeout(20000)
                    .execute().body();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ApiResult<?> getWxConfig() {
        String path = "/system/setting?settingKey=mp-weixin";
        try {
            // 链式构建请求
            final String body = HttpRequest.get(host.concat(path))
                    .header("Authorization", ACCESS_TOKEN)
                    .header("tenantId", TENANT_ID)
                    .timeout(20000)
                    .execute().body();
            return JSONUtil.parseObject(body, ApiResult.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
