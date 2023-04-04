FROM openjdk:17-alpine
RUN mkdir klebermart
ARG JAR_FILE
ADD /target/${JAR_FILE} /klebermart/gatewayserver.jar
WORKDIR /klebermart

ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom","-Xms2g","-Xmx8g","-jar","gatewayserver.jar"]