echo[INFO]开始打包...
set var= ..\
cd %var%
call mvn clean deploy -DskipTests=true
echo[INFO]打包结束

pause