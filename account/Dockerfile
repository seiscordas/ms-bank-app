FROM openjdk:17-alpine
RUN mkdir klebermart
ARG JAR_FILE
ADD /target/${JAR_FILE} /klebermart/account.jar
WORKDIR /klebermart

ENV PORT 8080 5003
EXPOSE 8080 5003

ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,address=5003,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom","-Xms2g","-Xmx8g","-jar","account.jar"]