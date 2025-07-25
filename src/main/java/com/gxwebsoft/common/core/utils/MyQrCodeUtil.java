package com.gxwebsoft.common.core.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import static com.gxwebsoft.common.core.constants.QRCodeConstants.*;

/**
 * 常用工具方法
 *
 * @author WebSoft
 * @since 2017-06-10 10:10:22
 */
public class MyQrCodeUtil {

  private static final String logoUrl = "https://file.wsdns.cn/20230430/6fa31aca3b0d47af98a149cf2dd26a4f.jpeg";

  /**
   * 生成用户二维码
   */
  public static String getUserCode(Integer userId, String content) throws IOException {
    return createQrCode(USER_QRCODE,userId,content);
  }

  /**
   * 生成工单二维码
   */
  public static String getTaskCode(Integer taskId, String content) throws IOException {
    return createQrCode(TASK_QRCODE,taskId,content);
  }

  /**
   * 生成商品二维码
   */
  public static String getGoodsCode(Integer goodsId, String content) throws IOException {
    return createQrCode(GOODS_QRCODE,goodsId,content);
  }

  /**
   * 生成自定义二维码
   */
  public static String getCodeMap(HashMap<String, String> map) throws IOException {
    return "";
  }

  /**
   * 生成带水印的二维码
   * @param type 类型
   * @param id 实体ID
   * @param content 二维码内容
   * @return 二维码图片地址
   */
  public static String createQrCode(String type,Integer id, String content) throws IOException {
    String filePath = "/www/wwwroot/file.ws/file/qrcode/".concat(type).concat("/");
    String qrcodeUrl = "https://file.websoft.top/qrcode/".concat(type).concat("/");
    // 将URL转为BufferedImage
    BufferedImage bufferedImage = ImageIO.read(new URL(logoUrl));
    // 生成二维码
    QrConfig config = new QrConfig(300, 300);
    // 设置边距，既二维码和背景之间的边距
    config.setMargin(1);
    // 附带小logo
    config.setImg(bufferedImage);
    // 保存路径
    filePath = filePath.concat(id + ".jpg");
    qrcodeUrl = qrcodeUrl.concat(id + ".jpg") + "?v=" + DateUtil.current();

    // 生成二维码
    QrCodeUtil.generate(content, config, FileUtil.file(filePath));
    return qrcodeUrl;
  }
}
