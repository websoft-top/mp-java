# 安全配置指南

本文档说明如何安全地配置和部署此项目。

## 🔒 环境变量配置

### 1. 创建环境变量文件

```bash
# 复制示例文件
cp .env.example .env

# 编辑环境变量文件
vim .env  # 或使用其他编辑器
```

### 2. 必需的环境变量

#### 数据库配置
```bash
DB_URL=jdbc:mysql://your-host:3306/your-database?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8
DB_USERNAME=your_username
DB_PASSWORD=your_strong_password
```

#### Redis配置
```bash
REDIS_HOST=your-redis-host
REDIS_PORT=6379
REDIS_PASSWORD=your_redis_password
```

#### JWT密钥配置
```bash
# 生成强随机密钥
JWT_TOKEN_KEY=$(openssl rand -base64 32)
```

## 📁 配置文件设置

### 1. 创建实际配置文件

```bash
# 从模板创建配置文件
cp src/main/resources/application.yml.template src/main/resources/application.yml
cp src/main/resources/application-dev.yml.template src/main/resources/application-dev.yml
cp src/main/resources/application-prod.yml.template src/main/resources/application-prod.yml
```

### 2. 配置文件说明

- `application.yml` - 主配置文件，包含通用配置
- `application-dev.yml` - 开发环境特定配置
- `application-prod.yml` - 生产环境特定配置

## 🔐 证书文件配置

### 1. 支付证书

将支付相关证书文件放置在 `src/main/resources/cert/` 目录下：

```
src/main/resources/cert/
├── alipayPublicCert.crt      # 支付宝公钥证书
├── alipayRootCert.crt        # 支付宝根证书
├── appPublicCert.crt         # 应用公钥证书
├── apiclient_cert.pem        # 微信支付API证书
├── apiclient_key.pem         # 微信支付API私钥
└── wechatpay_*.pem          # 微信支付平台证书
```

### 2. 证书文件权限

```bash
# 设置证书文件权限
chmod 600 src/main/resources/cert/*.pem
chmod 644 src/main/resources/cert/*.crt
```

## 🚀 部署配置

### 1. Docker部署

```dockerfile
# 在Dockerfile中设置环境变量
ENV DB_URL=jdbc:mysql://db:3306/modules
ENV DB_USERNAME=modules
ENV DB_PASSWORD=your_password
# ... 其他环境变量
```

### 2. 系统环境变量

```bash
# 在系统中设置环境变量
export DB_URL="jdbc:mysql://localhost:3306/modules"
export DB_USERNAME="your_username"
export DB_PASSWORD="your_password"
```

## ⚠️ 安全检查清单

### 部署前检查

- [ ] 所有敏感信息已从配置文件中移除
- [ ] 环境变量已正确设置
- [ ] 证书文件权限已正确配置
- [ ] `.env` 文件已添加到 `.gitignore`
- [ ] 数据库密码足够强壮
- [ ] JWT密钥使用强随机字符串
- [ ] Redis密码已设置
- [ ] 邮件服务器密码已更新

### 运行时检查

- [ ] 应用能正常启动
- [ ] 数据库连接正常
- [ ] Redis连接正常
- [ ] 文件上传功能正常
- [ ] 支付功能正常（如果使用）

## 🔧 故障排除

### 常见问题

1. **数据库连接失败**
   - 检查 `DB_URL`、`DB_USERNAME`、`DB_PASSWORD` 环境变量
   - 确认数据库服务正在运行

2. **Redis连接失败**
   - 检查 `REDIS_HOST`、`REDIS_PORT`、`REDIS_PASSWORD` 环境变量
   - 确认Redis服务正在运行

3. **JWT Token错误**
   - 检查 `JWT_TOKEN_KEY` 环境变量是否设置
   - 确保密钥是Base64编码的字符串

4. **文件上传失败**
   - 检查 `UPLOAD_PATH` 目录是否存在且有写权限
   - 确认磁盘空间充足

## 📞 支持

如果遇到配置问题，请检查：
1. 环境变量是否正确设置
2. 配置文件语法是否正确
3. 服务依赖是否正常运行
4. 日志文件中的错误信息
