package com.gxwebsoft;

import cn.hutool.core.util.NumberUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * Created by WebSoft on 2020-03-23 23:37
 */
@SpringBootTest
public class TestMain {
  /**
   * 生成唯一的key用于jwt工具类
   */
  @Test
  public void testGenJwtKey() {
    BigDecimal bigDecimal = new BigDecimal(NumberUtil.decimalFormat("0.00", 1 * 0.01));
    System.out.println("实际付款金额 = " + bigDecimal);
  }
}
