FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} wallet.jar
ENTRYPOINT ["java","-jar","/wallet.jar"]