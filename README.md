# Rate of Exchange

Hello. In this project, I took the official daily exchange rate of Central Bank of the Republic of Azerbaijan (CBAR) from the official website (https://www.cbar.az/currencies/25.05.2022.xml) and used it in my project.
  
  
  ## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/eg/java/technologies/javase/jdk11-archive-downloads.html)
- [Gradle](https://gradle.org/install/)


## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `az.example.rateofexchange.RateofExchangeApplication` class from your IDE.

If you have [Docker](https://www.docker.com/), you can run the project with the `docker compose up` command

Containers for both services will be launched. The project can be reached at http://localhost:8090.

 #№ After Running
  To use the generated apis, you can import the postman collection located in the path `Currency.postman_collection.json` into your postman application.
  
  Or you can visit the url `http://localhost:8090/swagger-ui.html' and send a request

  ## This project template uses:

* [Java](https://www.oracle.com/java/technologies/) programming language
* [Gradle](https://gradle.org/) to build and, in dev-mode, run the application with hot reload
* [Spring Web](https://spring.io/guides/gs/serving-web-content/) to serve HTTP requests
* [Spring JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/) for database access
* [Spring Security](https://spring.io/guides/gs/securing-web/) for secure HTTP reuqests
* [Liquibase](https://www.liquibase.org/) for database migrations
* [Swagger](https://swagger.io/tools/swagger-ui/download/) for api documentation
  
## The technology and libraries I use
Java, Spring Boot, Spring Security, PosgreSQL, Mapstruct, Apache Http Client, Javax Validation, Json web token, Springfox-swagger2, Liquibase, Lombok
 
## Some of the challenges i faced and features i hope to implement in the future.
1. By default PostgreSql cannot store LocalDate type directly in database by JPA. LocalDate type is stored as DATE type in PostgreSql
I added @Convert(converter = LocalDateAttributeConverter.class) annotation under LocalDate field in Entity class.

2. MapStruct was giving the error "Consider defining a bean of type 'az.example.rateofexchange.mapper.ValuteCursMapper' in your configuration".
I added  -> `annotationProcessor "org.mapstruct:mapstruct-processor:1.5.3.Final` in build.gradle file

3. When using Mapstruct. I have a class that contains a Set<B> class inside A class and a Set<C> class inside it
var when I map this class using Mapstruct it doesn't generate the codes for C class. You can look inside the `az.example.rateofexchange.mapper.ValuteCursMapper` interface to see the solution to this
  
