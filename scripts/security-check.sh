#!/bin/bash

# 安全检查脚本
# 用于检查项目是否包含敏感信息

echo "🔍 开始安全检查..."
echo ""

# 检查是否存在敏感配置文件
echo "📁 检查配置文件..."
SENSITIVE_FILES=(
    "src/main/resources/application.yml"
    "src/main/resources/application-dev.yml"
    "src/main/resources/application-prod.yml"
    ".env"
)

for file in "${SENSITIVE_FILES[@]}"; do
    if [ -f "$file" ]; then
        echo "⚠️  发现敏感文件: $file"
        echo "   请确保此文件不包含敏感信息或已添加到 .gitignore"
    fi
done

# 检查是否存在证书文件
echo ""
echo "🔐 检查证书文件..."
if [ -d "src/main/resources/cert" ]; then
    CERT_FILES=$(find src/main/resources/cert -name "*.pem" -o -name "*.crt" -o -name "*.key" 2>/dev/null)
    if [ -n "$CERT_FILES" ]; then
        echo "⚠️  发现证书文件:"
        echo "$CERT_FILES"
        echo "   请确保这些文件已添加到 .gitignore"
    else
        echo "✅ 未发现证书文件"
    fi
else
    echo "✅ 证书目录不存在"
fi

# 检查模板文件是否存在
echo ""
echo "📋 检查模板文件..."
TEMPLATE_FILES=(
    "src/main/resources/application.yml.template"
    "src/main/resources/application-dev.yml.template"
    "src/main/resources/application-prod.yml.template"
    ".env.example"
)

for file in "${TEMPLATE_FILES[@]}"; do
    if [ -f "$file" ]; then
        echo "✅ 模板文件存在: $file"
    else
        echo "❌ 模板文件缺失: $file"
    fi
done

# 检查 .gitignore 文件
echo ""
echo "🚫 检查 .gitignore 配置..."
if [ -f ".gitignore" ]; then
    if grep -q "application.yml" .gitignore && grep -q ".env" .gitignore && grep -q "cert/" .gitignore; then
        echo "✅ .gitignore 配置正确"
    else
        echo "⚠️  .gitignore 可能配置不完整"
        echo "   请确保包含以下内容:"
        echo "   - src/main/resources/application*.yml"
        echo "   - .env"
        echo "   - src/main/resources/cert/"
    fi
else
    echo "❌ .gitignore 文件不存在"
fi

# 搜索可能的硬编码敏感信息
echo ""
echo "🔎 搜索硬编码敏感信息..."
PATTERNS=(
    "password.*=.*[^{]"
    "secret.*=.*[^{]"
    "key.*=.*[^{]"
    "token.*=.*[^{]"
)

FOUND_ISSUES=false
for pattern in "${PATTERNS[@]}"; do
    RESULTS=$(grep -r -i --include="*.java" --include="*.yml" --include="*.yaml" --include="*.properties" "$pattern" src/ 2>/dev/null | grep -v template || true)
    if [ -n "$RESULTS" ]; then
        echo "⚠️  发现可能的硬编码敏感信息:"
        echo "$RESULTS"
        FOUND_ISSUES=true
    fi
done

if [ "$FOUND_ISSUES" = false ]; then
    echo "✅ 未发现硬编码敏感信息"
fi

echo ""
echo "🎯 安全检查完成!"
echo ""
echo "📝 提交前检查清单:"
echo "- [ ] 所有敏感配置文件已删除或添加到 .gitignore"
echo "- [ ] 证书文件已删除或添加到 .gitignore"
echo "- [ ] 模板文件已创建"
echo "- [ ] .env.example 文件已创建"
echo "- [ ] 无硬编码敏感信息"
echo "- [ ] README.md 已更新安全配置说明"
echo ""
echo "✅ 如果所有检查项都通过，项目可以安全提交到 GitHub！"
