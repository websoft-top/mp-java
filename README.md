<div align="center">
  <h1>🚀 WebSoft API</h1>
  <p><strong>基于 Spring Boot + MyBatis Plus 的企业级后端API服务</strong></p>

  <p>
    <img src="https://img.shields.io/badge/Java-1.8+-ED8B00" alt="Java">
    <img src="https://img.shields.io/badge/Spring%20Boot-2.5.4-6DB33F" alt="Spring Boot">
    <img src="https://img.shields.io/badge/MyBatis%20Plus-3.4.3-blue" alt="MyBatis Plus">
    <img src="https://img.shields.io/badge/MySQL-8.0+-4479A1" alt="MySQL">
    <img src="https://img.shields.io/badge/Redis-6.0+-DC382D" alt="Redis">
    <img src="https://img.shields.io/badge/License-MIT-blue" alt="License">
  </p>
</div>

## 📖 项目简介

WebSoft API 是一个基于 **Spring Boot + MyBatis Plus** 构建的现代化企业级后端API服务，采用最新的Java技术栈：

- **核心框架**：Spring Boot 2.5.4 + Spring Security + Spring AOP
- **数据访问**：MyBatis Plus 3.4.3 + Druid 连接池
- **数据库**：MySQL + Redis
- **文档工具**：Swagger 3.0 + Knife4j
- **工具库**：Hutool、Lombok、FastJSON

## 🔒 安全配置

⚠️ **重要提醒**：本项目已移除所有敏感信息，首次运行前请参考 [安全配置指南](SECURITY_SETUP.md) 进行配置。

### 快速配置步骤

1. **复制环境变量文件**：
   ```bash
   cp .env.example .env
   ```

2. **编辑环境变量**：
   ```bash
   # 编辑 .env 文件，填入实际的数据库、Redis等配置信息
   vim .env
   ```

3. **创建配置文件**：
   ```bash
   cp src/main/resources/application.yml.template src/main/resources/application.yml
   cp src/main/resources/application-dev.yml.template src/main/resources/application-dev.yml
   cp src/main/resources/application-prod.yml.template src/main/resources/application-prod.yml
   ```

4. **配置证书文件**（如需支付功能）：
   - 将支付证书文件放入 `src/main/resources/cert/` 目录
   - 参考 `src/main/resources/cert/README.md` 说明

### 📚 相关文档

- [安全配置指南](SECURITY_SETUP.md) - 详细的安全配置说明
- [安全处理总结](SECURITY_SUMMARY.md) - 项目安全措施总结



## 项目演示
| 后台管理系统 | https://mp.websoft.top                                                                                                              |
|--------|-------------------------------------------------------------------------------------------------------------------------------------|
| 测试账号   | 13800010123,123456
| 正式账号 | [立即注册](https://mp.websoft.top/register/?inviteCode=github)                                                                          |
| 关注公众号  | ![输入图片说明](https://oss.wsdns.cn/20240327/f1175cc5aae741d3af05484747270bd5.jpeg?x-oss-process=image/resize,m_fixed,w_150/quality,Q_90) |




## 🛠️ 技术栈

### 核心框架
| 技术 | 版本 | 说明 |
|------|------|------|
| Java | 1.8+ | 编程语言 |
| Spring Boot | 2.5.4 | 微服务框架 |
| Spring Security | 5.5.x | 安全框架 |
| MyBatis Plus | 3.4.3 | ORM框架 |
| MySQL | 8.0+ | 关系型数据库 |
| Redis | 6.0+ | 缓存数据库 |
| Druid | 1.2.6 | 数据库连接池 |

### 功能组件
- **Swagger 3.0 + Knife4j** - API文档生成与测试
- **JWT** - 用户认证与授权
- **Hutool** - Java工具类库
- **EasyPOI** - Excel文件处理
- **阿里云OSS** - 对象存储服务
- **微信支付/支付宝** - 支付集成
- **Socket.IO** - 实时通信
- **MQTT** - 物联网消息传输

## 📋 环境要求

### 基础环境
- ☕ **Java 1.8+**
- 🗄️ **MySQL 8.0+**
- 🔴 **Redis 6.0+**
- 📦 **Maven 3.6+**

### 开发工具
- **推荐**：IntelliJ IDEA / Eclipse
- **插件**：Lombok Plugin、MyBatis Plugin

## 🚀 快速开始

### 1. 克隆项目
```bash
git clone https://github.com/websoft-top/mp-java.git
cd mp-java
```

### 2. 安全配置（必需）
⚠️ **首次运行前必须完成安全配置**

```bash
# 复制环境变量示例文件
cp .env.example .env

# 编辑环境变量文件，填入实际配置
vim .env

# 从模板创建配置文件
cp src/main/resources/application.yml.template src/main/resources/application.yml
cp src/main/resources/application-dev.yml.template src/main/resources/application-dev.yml
cp src/main/resources/application-prod.yml.template src/main/resources/application-prod.yml
```

详细配置说明请参考：[安全配置指南](SECURITY_SETUP.md)

### 3. 数据库配置
```sql
-- 创建数据库
CREATE DATABASE websoft_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 导入数据库脚本（如果有的话）
-- source /path/to/database.sql
```

### 4. 环境变量配置
在 `.env` 文件中配置必要的环境变量：
```bash
# 数据库配置
DB_URL=jdbc:mysql://localhost:3306/websoft_db?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8
DB_USERNAME=your_username
DB_PASSWORD=your_password

# Redis配置
REDIS_HOST=localhost
REDIS_PORT=6379
REDIS_PASSWORD=your_redis_password

# JWT密钥（必需）
JWT_TOKEN_KEY=your_jwt_secret_key_base64_encoded
```

### 5. 启动项目
```bash
# 使用 Maven 启动
mvn spring-boot:run

# 或者使用 IDE 直接运行 WebSoftApplication.java
```

访问 `http://localhost:9200` 即可看到API服务。

### 5. API文档
启动项目后，访问以下地址查看API文档：
- Swagger UI: `http://localhost:9200/swagger-ui/index.html`
- Knife4j: `http://localhost:9200/doc.html`

## ⚙️ 配置说明

### 数据库配置
在 `application-dev.yml` 中配置数据库连接：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/websoft_db
    username: root
    password: your_password
    driver-class-name: com.mysql.cj.jdbc.Driver
```

### Redis配置
```yaml
spring:
  redis:
    host: localhost
    port: 6379
    password: your_redis_password
    database: 0
```

### 阿里云OSS配置
```yaml
config:
  endpoint: https://oss-cn-shenzhen.aliyuncs.com
  accessKeyId: your_access_key_id
  accessKeySecret: your_access_key_secret
  bucketName: your_bucket_name
  bucketDomain: https://your-domain.com
```

### 其他配置
- **JWT密钥**：`config.token-key` 用于JWT令牌加密
- **文件上传路径**：`config.upload-path` 本地文件存储路径
- **邮件服务**：配置SMTP服务器用于发送邮件

## 🎯 核心功能

### 🔐 用户认证与授权
- **JWT认证**：基于JSON Web Token的用户认证
- **Spring Security**：完整的安全框架集成
- **角色权限**：基于RBAC的权限控制
- **图形验证码**：防止恶意登录

### 📝 内容管理系统(CMS)
- **文章管理**：支持富文本内容管理
- **媒体文件**：图片/视频文件上传与管理
- **分类管理**：内容分类与标签管理
- **SEO优化**：搜索引擎优化支持

### 🛒 电商系统
- **商品管理**：商品信息、规格、库存管理
- **订单系统**：完整的订单流程管理
- **支付集成**：支持微信支付、支付宝
- **物流跟踪**：快递100物流查询集成

### 🔧 系统管理
- **用户管理**：用户信息维护与管理
- **系统配置**：动态配置管理
- **日志监控**：系统操作日志记录
- **数据备份**：数据库备份与恢复

### 📊 数据分析
- **统计报表**：业务数据统计分析
- **图表展示**：数据可视化展示
- **导出功能**：Excel数据导出
- **实时监控**：系统性能监控

## 🏗️ 项目结构

```
src/main/java/com/gxwebsoft/
├── WebSoftApplication.java    # 启动类
├── cms/                      # 内容管理模块
│   ├── controller/          # 控制器层
│   ├── service/            # 业务逻辑层
│   ├── mapper/             # 数据访问层
│   └── entity/             # 实体类
├── shop/                    # 商城模块
│   ├── controller/
│   ├── service/
│   ├── mapper/
│   └── entity/
├── common/                  # 公共模块
│   ├── core/               # 核心配置
│   ├── utils/              # 工具类
│   └── exception/          # 异常处理
└── resources/
    ├── application.yml     # 主配置文件
    ├── application-dev.yml # 开发环境配置
    └── application-prod.yml# 生产环境配置
```

## 🔧 开发规范

### 代码结构
- **Controller层**：处理HTTP请求，参数验证
- **Service层**：业务逻辑处理，事务管理
- **Mapper层**：数据访问，SQL映射
- **Entity层**：数据实体，数据库表映射

### 命名规范
- **类名**：使用大驼峰命名法（PascalCase）
- **方法名**：使用小驼峰命名法（camelCase）
- **常量**：使用全大写，下划线分隔
- **包名**：使用小写字母，点分隔

## 📚 API文档

项目集成了Swagger和Knife4j，提供完整的API文档：

### 访问地址
- **Swagger UI**: `http://localhost:9200/swagger-ui/index.html`
- **Knife4j**: `http://localhost:9200/doc.html`

### 主要接口模块
- **用户认证**: `/api/auth/**` - 登录、注册、权限验证
- **用户管理**: `/api/user/**` - 用户CRUD操作
- **内容管理**: `/api/cms/**` - 文章、媒体文件管理
- **商城管理**: `/api/shop/**` - 商品、订单管理
- **系统管理**: `/api/system/**` - 系统配置、日志管理

## 🚀 部署指南

### 开发环境部署
```bash
# 1. 启动MySQL和Redis服务
# 2. 创建数据库并导入初始数据
# 3. 修改配置文件
# 4. 启动应用
mvn spring-boot:run
```

### 生产环境部署
```bash
# 1. 打包应用
mvn clean package -Dmaven.test.skip=true

# 2. 运行jar包
java -jar target/com-gxwebsoft-modules-1.5.0.jar --spring.profiles.active=prod

# 3. 使用Docker部署（可选）
docker build -t websoft-api .
docker run -d -p 9200:9200 websoft-api
```

## 🤝 贡献指南

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 打开 Pull Request

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

## 📞 联系我们

- 官网：https://websoft.top
- 邮箱：170083662@qq.top
- QQ群：479713884

---

⭐ 如果这个项目对您有帮助，请给我们一个星标！