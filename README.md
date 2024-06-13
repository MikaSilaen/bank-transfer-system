<h1 align="center">
  Bank Transfer System
</h1>

## Table of Contents ##
1. [Spring Boot](#spring-soot)
2. [Technology](#technology)
3. [Application Structure](#application-structure)
4. [Run Locally](#running-the-server-locally)
5. [API Documentation](#api-documentation)
6. [Contributors](#contributors)

## Spring Boot ##
_Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can just run. We take an opinionated view of the Spring platform and third-party libraries so you can get started with minimum fuss. Most Spring Boot applications need very little Spring configuration._

## Technology ##
Following libraries were used during the development of this boilerplate :

- **Spring Boot** - Server side framework
- **Swagger** - API documentation

##### Others #####
- This project using lombok project as class processor, you need to install the plugins to your IDE. Details you can find here - [eclipse](https://projectlombok.org/setup/eclipse) or [Intellij](https://projectlombok.org/setup/intellij)

## Application Structure ##
Spring Boot is an opinionated framework that makes our life very easy since we don't have to choose the versions of different dependencies based on the version of Spring framework, its all taken care of by Spring Boot.  The structure look as follows :

## Response and Exception Handling ##
### Request Payload
```java
curl --location 'http://localhost:8080/api/transaction/transfer' \
        --header 'accept: */*' \
        --header 'Content-Type: application/json' \
        --data '{
        "username": "john_doe",
        "password": "password123",
        "accountNoSender": "1234567890",
        "pin": "1234",
        "accountNoDestination": "5678912340",
        "amount": 50000.00
        }'
```
### Response Payload
```java
response code: 200
        {
        "status": "ok",
        "code": "00",
        "message": "success",
        "data": {
        "transactionId": "2e295f3a-e488-4a3d-a029-24c35090cb12",
        "message": "Bank Transfer to account 5678912340 was successful"
        }
        }

```
### Exception Payload
```java
response code: 500
        {
        "status": "error",
        "code": "01",
        "message": "Password is not match",
        "data": null
        }
``` 

## Running the server locally ##
To be able to run this Spring Boot app you will need to first build it. To build and package a Spring Boot app into a single executable Jar file with a Maven, use the below command. You will need to run it from the project folder which contains the pom.xml file.

```
maven package
```
or you can also use

```
mvn install
```

To run the Spring Boot app from a command line in a Terminal window you can you the java -jar command. This is provided your Spring Boot app was packaged as an executable jar file.

```
java -jar target/bank-transfer-system-0.0.1-SNAPSHOT.jar
```

You can also use Maven plugin to run the app. Use the below example to run your Spring Boot app with Maven plugin :

```
mvn spring-boot:run
```

You can follow any/all of the above commands, or simply use the run configuration provided by your favorite IDE and run/debug the app from there for development purposes. Once the server is setup you should be able to access the API at the following URL :

http://localhost:8080

## API Documentation ##
Its as important to document(as is the development) and make your APIs available in a readable manner for frontend teams or external consumers. The tool for API documentation used in this starter kit is Swagger3, you can open the same inside a browser at the following url -

- http://localhost:8080 or
- http://localhost:8080/swagger-ui/index.html

## Contributors ##
[Mika Sularti Silaen](mailto:mika.silaen23@gmail.com)