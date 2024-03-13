echo "start"
echo %cd%
"chmod +x .\make_linux.sh"
"chmod +x .\wechat-server\make_launcher.sh"
START  /wait bash .\wechat-server\make_launcher.sh > log.txt
"./wechat-server/target/runtime/bin/java" -jar wechat-server/target/levelup-0.0.1-SNAPSHOT.jar 
pause