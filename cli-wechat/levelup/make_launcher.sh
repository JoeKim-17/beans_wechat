rm -r bin
rm -r conf
rm -r include
rm -r lib
rm -r man

mvn clean 
mvn dependency:copy -Dartifact='com.google.code.gson:gson:2.10.1'
mvn package
mvn jlink:jlink