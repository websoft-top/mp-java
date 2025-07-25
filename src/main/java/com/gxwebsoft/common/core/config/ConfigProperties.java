package com.gxwebsoft.common.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 系统配置属性
 *
 * @author WebSoft
 * @since 2021-08-30 17:58:16
 */
@Data
@ConfigurationProperties(prefix = "config")
public class ConfigProperties {
  /**
   * 文件上传磁盘位置
   */
  private Integer uploadLocation = 0;

  /**
   * 文件上传是否使用uuid命名
   */
  private Boolean uploadUuidName = true;

  /**
   * 文件上传生成缩略图的大小(kb)
   */
  private Integer thumbnailSize = 60;

  /**
   * OpenOffice的安装目录
   */
  private String openOfficeHome;

  /**
   * swagger扫描包
   */
  private String swaggerBasePackage;

  /**
   * swagger文档标题
   */
  private String swaggerTitle;

  /**
   * swagger文档描述
   */
  private String swaggerDescription;

  /**
   * swagger文档版本号
   */
  private String swaggerVersion;

  /**
   * swagger地址
   */
  private String swaggerHost;

  /**
   * token过期时间, 单位秒
   */
  private Long tokenExpireTime = 60 * 60 * 30 * 24L;

  /**
   * token快要过期自动刷新时间, 单位分钟
   */
  private int tokenRefreshTime = 30;

  /**
   * 生成token的密钥Key的base64字符
   */
  private String tokenKey;

  /**
   * 文件上传目录
   */
  private String uploadPath;

  /**
   * 本地文件上传目录(开发环境)
   */
  private String localUploadPath;

  /**
   * 文件服务器
   */
  private String fileServer;

  /**
   * 网关地址
   */
  private String serverUrl;

  /**
   * 阿里云存储 OSS
   * Endpoint
   */
  private String endpoint;
  private String accessKeyId;
  private String accessKeySecret;
  private String bucketName;
  private String bucketDomain;

}
