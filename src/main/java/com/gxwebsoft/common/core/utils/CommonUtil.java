package com.gxwebsoft.common.core.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.gxwebsoft.common.core.Constants;
import com.gxwebsoft.common.core.web.ApiResult;
import com.gxwebsoft.common.system.entity.Role;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 常用工具方法
 *
 * @author WebSoft
 * @since 2017-06-10 10:10:22
 */
public class CommonUtil {

    // 生成uuid的字符
    private static final String[] chars = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
            "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    /**
     * 生成8位uuid
     *
     * @return String
     */
    public static String randomUUID8() {
        StringBuilder sb = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            sb.append(chars[x % 0x3E]);
        }
        return sb.toString();
    }

    /**
     * 生成16位uuid
     *
     * @return String
     */
    public static String randomUUID16() {
        StringBuilder sb = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 16; i++) {
            String str = uuid.substring(i * 2, i * 2 + 2);
            int x = Integer.parseInt(str, 16);
            sb.append(chars[x % 0x3E]);
        }
        return sb.toString();
    }

    /**
     * 获取当前时间
     *
     * @return String
     */
    public static String currentTime() {
      Date date = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
      return sdf.format(date);
    }

    /**
     * 生成10位随机用户名
     *
     * @return String
     */
    public static String randomUsername(String prefix) {
      Date date = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
      String currentTime = sdf.format(date);
      return prefix + currentTime;
    }

    /**
     * 生成订单号
     * 20233191166110426
     * 20230419135802391412
     * @return
     */
    public static String createOrderNo() {
      String prefix = DateTime.now().toString(DatePattern.PURE_DATETIME_PATTERN);
      return prefix + RandomUtil.randomNumbers(2);
    }

    /**
     * 生成订单号
     * @param tenantId
     * 20233191166110426
     * 20230419135802391412
     * @return
     */
      public static String createOrderNo(String tenantId) {
        String prefix = DateTime.now().toString(DatePattern.PURE_DATETIME_PATTERN);
        return prefix + tenantId + RandomUtil.randomNumbers(2);
    }

    /**
     * 生成订单水流号
     * @param tenantId
     * @return
     */
    public static String serialNo(int tenantId) {
      String prefix = DateTime.now().toString(DatePattern.PURE_DATETIME_PATTERN);
      return prefix + tenantId + RandomUtil.randomNumbers(2);
    }

    /**
     * 生成会员卡号
     * @return
     */
    public static String createCardNo() {
      String prefix = DateTime.now().toString(DatePattern.PURE_TIME_PATTERN);
      return "00" + prefix + RandomUtil.randomNumbers(2);
    }

    /**
     * 检查List是否有重复元素
     *
     * @param list   List
     * @param mapper 获取需要检查的字段的Function
     * @param <T>    数据的类型
     * @param <R>    需要检查的字段的类型
     * @return boolean
     */
    public static <T, R> boolean checkRepeat(List<T> list, Function<? super T, ? extends R> mapper) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i != j && mapper.apply(list.get(i)).equals(mapper.apply(list.get(j)))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * List转为树形结构
     *
     * @param data           List
     * @param parentId       顶级的parentId
     * @param parentIdMapper 获取parentId的Function
     * @param idMapper       获取id的Function
     * @param consumer       赋值children的Consumer
     * @param <T>            数据的类型
     * @param <R>            parentId的类型
     * @return List<T>
     */
    public static <T, R> List<T> toTreeData(List<T> data, R parentId,
                                            Function<? super T, ? extends R> parentIdMapper,
                                            Function<? super T, ? extends R> idMapper,
                                            BiConsumer<T, List<T>> consumer) {
        List<T> result = new ArrayList<>();
        for (T d : data) {
            R dParentId = parentIdMapper.apply(d);
            if (ObjectUtil.equals(parentId, dParentId)) {
                R dId = idMapper.apply(d);
                List<T> children = toTreeData(data, dId, parentIdMapper, idMapper, consumer);
                consumer.accept(d, children);
                result.add(d);
            }
        }
        return result;
    }

    /**
     * 遍历树形结构数据
     *
     * @param data     List
     * @param consumer 回调
     * @param mapper   获取children的Function
     * @param <T>      数据的类型
     */
    public static <T> void eachTreeData(List<T> data, Consumer<T> consumer, Function<T, List<T>> mapper) {
        for (T d : data) {
            consumer.accept(d);
            List<T> children = mapper.apply(d);
            if (children != null && children.size() > 0) {
                eachTreeData(children, consumer, mapper);
            }
        }
    }

    /**
     * 获取集合中的第一条数据
     *
     * @param records 集合
     * @return 第一条数据
     */
    public static <T> T listGetOne(List<T> records) {
        return records == null || records.size() == 0 ? null : records.get(0);
    }

    /**
     * 支持跨域
     *
     * @param response HttpServletResponse
     */
    public static void addCrossHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Expose-Headers", Constants.TOKEN_HEADER_NAME);
    }

    /**
     * 输出错误信息
     *
     * @param response HttpServletResponse
     * @param code     错误码
     * @param message  提示信息
     * @param error    错误信息
     */
    public static void responseError(HttpServletResponse response, Integer code, String message, String error) {
        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write(JSONUtil.toJSONString(new ApiResult<>(code, message, null, error)));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean hasRole(List<Role> array,String value){
      System.out.println("value = " + value);
      if (value == null) {
        return true;
      }
      if (array == null) {
        return false;
      }
      if (!array.isEmpty()) {
        final List<String> collect = array.stream().map(Role::getRoleCode)
          .collect(Collectors.toList());
        final boolean contains = collect.contains(value);
        if (contains) {
          return true;
        }
      }
      return false;
    }

  public static boolean hasRole(List<Role> array,List<String> value){
    System.out.println("value = " + value);
    if (value == null) {
      return true;
    }
    if (array == null) {
      return false;
    }
    if (!array.isEmpty()) {
      final List<String> collect = array.stream().map(Role::getRoleCode)
        .collect(Collectors.toList());
      final boolean disjoint = Collections.disjoint(collect, value);
      if (!disjoint) {
        return true;
      }
    }
    return false;
  }

  public static AES aes(){
    // 随机生成密钥
    byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
    return SecureUtil.aes(key);
  }

  // 机密文本
  public static String encrypt(String text){
    final AES aes = aes();
    return aes.encryptHex(text);
  }

  // 解密
  public static String decrypt(String encrypt){
    final AES aes = aes();
    return aes.decryptStr(encrypt, CharsetUtil.CHARSET_UTF_8);
  }

  /**
   * 验证给定的字符串是否为有效的中国大陆手机号码。
   *
   * @param phoneNumber 要验证的电话号码字符串
   * @return 如果字符串是有效的手机号码，则返回true；否则返回false
   */
  public static boolean isValidPhoneNumber(String phoneNumber) {
    // 定义手机号码的正则表达式
    String regex = "^1[3-9]\\d{9}$";

    // 创建Pattern对象
    Pattern pattern = Pattern.compile(regex);

    // 使用matcher方法创建Matcher对象并进行匹配
    return pattern.matcher(phoneNumber).matches();
  }
}
