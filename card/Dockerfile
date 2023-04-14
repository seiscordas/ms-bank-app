FROM openjdk:17-alpine
RUN mkdir klebermart
ARG JAR_FILE
ADD /target/${JAR_FILE} /klebermart/card.jar
WORKDIR /klebermart

#ENV PORT 9000 5004
#EXPOSE 9000 5004

ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom","-Xms2g","-Xmx8g","-jar","card.jar"]