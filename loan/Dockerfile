FROM openjdk:17-alpine
RUN mkdir klebermart
ARG JAR_FILE
ADD /target/${JAR_FILE} /klebermart/loan.jar
WORKDIR /klebermart

#ENV PORT 8090 5005
#EXPOSE 8090 5005

ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n","-Djava.security.egd=file:/dev/./urandom","-Xms2g","-Xmx8g","-jar","loan.jar"]