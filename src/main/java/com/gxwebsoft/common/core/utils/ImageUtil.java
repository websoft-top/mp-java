package com.gxwebsoft.common.core.utils;

import cn.hutool.core.codec.Base64Encoder;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.io.File;

public class ImageUtil {
    public  static  String ImageBase64(String imgUrl) {
        URL url = null;
        InputStream is = null;
        ByteArrayOutputStream outStream = null;
        HttpURLConnection httpUrl = null;
        try{
            url = new URL(imgUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            httpUrl.getInputStream();
            is = httpUrl.getInputStream();

            outStream = new ByteArrayOutputStream();
            //创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            //使用一个输入流从buffer里把数据读取出来
            while( (len=is.read(buffer)) != -1 ){
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
            // 对字节数组Base64编码
            return new Base64Encoder().encode(outStream.toByteArray());
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if(is != null)
            {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outStream != null)
            {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(httpUrl != null)
            {
                httpUrl.disconnect();
            }
        }
        return imgUrl;
    }


  public static void adjustQuality(File inputFile, File outputFile, float quality) throws IOException {
    // 读取图片文件
    BufferedImage image = ImageIO.read(inputFile);

    // 获取JPEG ImageWriters的迭代器
    Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpeg");
    ImageWriter writer = iter.next();

    // 创建输出文件
    ImageOutputStream ios = ImageIO.createImageOutputStream(outputFile);
    writer.setOutput(ios);

    // 创建ImageWriteParam并设置压缩质量
    ImageWriteParam iwp = writer.getDefaultWriteParam();
    if (iwp.canWriteCompressed()) {
      iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
      iwp.setCompressionQuality(quality); // 设置质量，1.0为最好，0.0最差
    }

    // 写入图片
    writer.write(null, new IIOImage(image, null, null), iwp);
    writer.dispose();
    ios.close();
  }

}

