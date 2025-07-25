#!/bin/bash

# 生成JWT密钥脚本
# 用于生成安全的JWT Token密钥

echo "🔐 生成JWT Token密钥..."

# 检查是否安装了openssl
if ! command -v openssl &> /dev/null; then
    echo "❌ 错误: 未找到 openssl 命令"
    echo "请安装 openssl:"
    echo "  - macOS: brew install openssl"
    echo "  - Ubuntu/Debian: sudo apt-get install openssl"
    echo "  - CentOS/RHEL: sudo yum install openssl"
    exit 1
fi

# 生成32字节的随机密钥并进行base64编码
JWT_KEY=$(openssl rand -base64 32)

echo "✅ JWT Token密钥生成成功!"
echo ""
echo "请将以下密钥添加到您的 .env 文件中:"
echo ""
echo "JWT_TOKEN_KEY=$JWT_KEY"
echo ""
echo "或者直接运行以下命令添加到 .env 文件:"
echo "echo 'JWT_TOKEN_KEY=$JWT_KEY' >> .env"
echo ""
echo "⚠️  请妥善保管此密钥，不要泄露给他人！"
