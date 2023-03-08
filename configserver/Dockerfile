FROM openjdk:17-alpine
RUN mkdir ms-bank-app
ARG JAR_FILE
ADD /target/${JAR_FILE} /ms-bank-app/configserver.jar
WORKDIR /ms-bank-app

ENV PORT 8071 5001
EXPOSE 8071 5001

ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=5001,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom","-Xms2g","-Xmx8g","-jar","configserver.jar"]