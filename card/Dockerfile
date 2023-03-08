FROM openjdk:17-alpine
RUN mkdir ms-bank-app
ARG JAR_FILE
ADD /target/${JAR_FILE} /ms-bank-app/card.jar
WORKDIR /ms-bank-app

ENV PORT 9000 5003
EXPOSE 9000 5003

ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=5003,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom","-Xms2g","-Xmx8g","-jar","card.jar"]