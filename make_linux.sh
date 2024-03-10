echo "start"
chmod +x ./wechat-server/make_launcher.sh
# cd wechat-server
./wechat-server/make_launcher.sh > ./log.out
pause
./wechat-server/target/runtime/bin/java -jar wechat-server/target/levelup-0.0.1-SNAPSHOT.jar 
# pause