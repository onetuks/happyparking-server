FROM openjdk:17-jdk
WORKDIR /server
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} happyparking.jar
EXPOSE 8080
CMD ["java", "-jar", "/server/happyparking.jar"]