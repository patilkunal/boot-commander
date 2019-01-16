FROM java:8
MAINTAINER Kunal Patil <kunalpatil@yahoo.com>

VOLUME /tmp
RUN "mkdir /app"
ADD target/boot-commander-0.0.1-SNAPSHOT.jar /app/boot-commander.jar

EXPOSE 8080

WORKDIR /app
CMD ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/boot-commander.jar"]

