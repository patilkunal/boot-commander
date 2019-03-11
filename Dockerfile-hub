FROM maven:3.3-jdk-8-onbuild 
VOLUME ["$HOME/.m2"]

FROM java:8
MAINTAINER Kunal Patil <kunalpatil@yahoo.com>

VOLUME /tmp
RUN mkdir /app
COPY --from=0 /usr/src/app/target/boot-commander-0.0.1-SNAPSHOT.jar /app/boot-commander.jar

EXPOSE 8080

WORKDIR /app
CMD ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/boot-commander.jar"]

