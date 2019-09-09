FROM openjdk:8
MAINTAINER SANKET
COPY target/spring-boot-apache-derby-docker.jar spring-boot-apache-derby-docker.jar
ENTRYPOINT ["java","-jar","spring-boot-apache-derby-docker.jar"]
