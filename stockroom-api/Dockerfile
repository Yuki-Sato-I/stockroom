FROM maven:3.8.6-eclipse-temurin-17-alpine

WORKDIR /stockroom-api/

CMD mvn clean install -Dmaven.test.skip=true && \
cp target/*.jar app.jar && \
java -jar app.jar