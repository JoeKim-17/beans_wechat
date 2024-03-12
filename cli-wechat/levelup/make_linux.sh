echo "start"
./make_launcher.sh #> ./log.out
./target/maven-jlink/default/bin/java -jar target/levelup-1.0-SNAPSHOT.jar
# pause