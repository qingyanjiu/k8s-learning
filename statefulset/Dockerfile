FROM maven:3
COPY . /web/
WORKDIR /web
RUN mvn package -Dmaven.test.skip=true

FROM openjdk:8-jdk-alpine
COPY --from=0 /web/target/*.jar /web/web.jar
WORKDIR /web
CMD java -jar web.jar