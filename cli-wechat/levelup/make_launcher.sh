rm -r bin
rm -r conf
rm -r include
rm -r lib
rm -r man

mvn clean
mvn compile
mvn package
mvn jlink:jlink