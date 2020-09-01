FROM registry.cn-shanghai.aliyuncs.com/shawndodo/gradle:5.6.2-jdk11
COPY ./ /app
WORKDIR /app
RUN gradle build -x test

FROM daocloud.io/library/java:openjdk-8-jre-alpine
RUN mkdir /app
VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY --from=0 /app/${JAR_FILE} /app/app.jar
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app/app.jar"]

MAINTAINER paopaoyue.cn@gmail.com