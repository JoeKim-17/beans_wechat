mvn clean install
mvn dependency:copy -Dartifact='com.google.code.gson:gson:2.10.1'
mvn package
mvn jlink:jlink