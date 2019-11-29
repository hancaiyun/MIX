echo off
echo [INFO]请输入需要升级的版本:
set /p newVersion=
echo [INFO]输入%newVersion% 开始替换版本
set var= ..\
cd %var%
call mvn clean versions:set -DnewVersion=%newVersion%

pause