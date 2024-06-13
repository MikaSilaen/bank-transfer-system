ARG BASE_IMAGE_SPRING
FROM openjdk:8-jre-alpine
ENV java_opts=""
ENV java_args=""
LABEL maintainer=""
WORKDIR /app
COPY target/bank-transfer-system*.jar /app/app.jar
ENTRYPOINT exec java $java_opts -jar app.jar $java_args
EXPOSE 8080
