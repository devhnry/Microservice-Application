# Starting with a base image containing java runtime
FROM openjdk:21-jdk-slim

#The person maintaing the dockker file
MAINTAINER devhnry.com

#Add the appication's jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]