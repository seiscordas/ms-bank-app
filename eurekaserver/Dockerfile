FROM openjdk:17-alpine
RUN mkdir klebermart
ARG JAR_FILE
ADD /target/${JAR_FILE} /klebermart/eurekaserver.jar
WORKDIR /klebermart

ENV PORT 8070 5002
EXPOSE 8070 5002

ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=5002,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom","-Xms2g","-Xmx8g","-jar","eurekaserver.jar"]