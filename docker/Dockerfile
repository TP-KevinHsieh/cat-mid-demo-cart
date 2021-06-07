FROM openjdk:alpine

WORKDIR /app

ARG JAR_FILE=*.jar

COPY ${JAR_FILE} /app/eval_be_api.jar
COPY properties/application.properties /app/eval-be-conf/application.properties
COPY properties/application-stage.properties /app/eval-be-conf/application-stage.properties

CMD ["java","-jar","/app/eval_be_api.jar"]