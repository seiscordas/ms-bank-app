FROM openjdk:17-alpine
RUN mkdir ms-bank-app
ARG JAR_FILE
ADD /target/${JAR_FILE} /ms-bank-app/loan.jar
WORKDIR /ms-bank-app

ENV PORT 8090 5002
EXPOSE 8090 5002

ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=5002,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom","-Xms2g","-Xmx8g","-jar","loan.jar"]