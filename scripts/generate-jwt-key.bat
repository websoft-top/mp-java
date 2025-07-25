@echo off
chcp 65001 >nul

echo 🔐 生成JWT Token密钥...
echo.

REM 检查是否安装了PowerShell
where powershell >nul 2>nul
if %errorlevel% neq 0 (
    echo ❌ 错误: 未找到 PowerShell
    echo 请确保 PowerShell 已安装
    pause
    exit /b 1
)

REM 使用PowerShell生成随机密钥
for /f "delims=" %%i in ('powershell -command "[Convert]::ToBase64String((1..32 | ForEach-Object { Get-Random -Maximum 256 }))"') do set JWT_KEY=%%i

echo ✅ JWT Token密钥生成成功!
echo.
echo 请将以下密钥添加到您的 .env 文件中:
echo.
echo JWT_TOKEN_KEY=%JWT_KEY%
echo.
echo 或者手动复制上面的密钥到 .env 文件
echo.
echo ⚠️  请妥善保管此密钥，不要泄露给他人！
echo.
pause
