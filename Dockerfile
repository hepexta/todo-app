FROM adoptopenjdk:11-jre-hotspot
MAINTAINER hepexta.com

ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} application.jar
COPY frontend/dist/ static/

ENTRYPOINT ["java", "-jar", "application.jar"]