FROM openjdk:17-alpine
RUN mkdir ms-bank-app
ARG JAR_FILE
ADD /target/${JAR_FILE} /ms-bank-app/account.jar
WORKDIR /ms-bank-app

ENV PORT 8080 5005
EXPOSE 8080 5005

ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom","-Xms2g","-Xmx8g","-jar","account.jar"]