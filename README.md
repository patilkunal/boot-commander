# How to build and run the Test Commander application written using Spring Boot

Designed and Developed by Kunal Patil (kunalpatil@yahoo.com) (kunal.patil@inovisionsoftware.com)

Showcase to show how to create REST API based application using Spring Boot, Spring Security authentication using JWT, Data access layer using Spring JPA.

And How to write JUnit tests, integration tests using Spring Test and integration tests using SOAP UI
       
Feel free to fork/clone the application to learn. 

All the code is Copyright of Inovision Software LLC.    



## Run Spring-Boot application manually

Start HSQL Database in terminal window

```
cd db/hsqldb
./dbserver.sh
```

Start the backend application in second terminal window (add -DskipTests to skip JUnit tests)

``` 
mvn spring-boot:run
```

## Run Spring-Boot using Docker

Build the docker images for HSQL Database and Spring boot application (make sure to include the '.' (dot) to specify the context of the build)

```
docker build -t boot-commander .
```

```
cd db/hsqldb
docker build -t hsqldb-server .
```

Create docker volumes to persist the HSQL DB data and then run the HSQL DB server

```
docker volume create testcasedb-data
docker run -d --rm -p 9001:9001 --name hsqldb -v testcasedb-data:/data hsqldb-server

```

Run the Test Commander application

```
docker run -d --rm -p 8080:8080 --name boot-commander boot-commander
```

## Running Spring-Boot using docker-compose

1. Build the application using maven package. 
2. Create the external volume to hold the DB data
3. Bring the application stack using docker-compose

```
mvn package
docker volume create testcasedb-data
docker-compose up
```

## Run the Angular UI application

1. Make sure Spring-Boot application is running using either of method above
2. Download and install latest Node JS
3. Change to src/main/ngapp directory and run following commands

```
npm install
npm run start
```
4. Login to application using default username and password (admin/pass123)
5. Click Categories and Host menu to see in action (Other menus are yet to be implemented)
6. Click logout 



## Run integration tests using SOAP UI

1. Download and install SOAP UI
2. Open ./soapui/BootCommander-soapui-project.xml


