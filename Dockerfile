FROM java
MAINTAINER keling "jingkeling@foxmail.com"

COPY ./target/chathub.jar /chathub.jar

ENTRYPOINT ["java", "-jar", "/chathub.jar"]