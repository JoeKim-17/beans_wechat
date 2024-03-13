rm -r bin
rm -r conf
rm -r include
rm -r lib
rm -r man

mvn clean
mvn package
mvn jlink:jlink

unzip -d target/runtime/ target/levelup-0.0.1-SNAPSHOT.zip