echo off
echo [INFO]��������Ҫ�����İ汾:
set /p newVersion=
echo [INFO]����%newVersion% ��ʼ�滻�汾
set var= ..\
cd %var%
call mvn clean versions:set -DnewVersion=%newVersion%

pause