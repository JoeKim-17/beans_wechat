echo "start"
START  /wait bash .\make_launcher.sh > log.out
"./target/maven-jlink/default/bin/java" -jar target/levelup-1.0-SNAPSHOT.jar 
@REM pause