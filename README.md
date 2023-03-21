# Rate of Exchange

Hello. In this project, I took the official daily exchange rate of Central Bank of the Republic of Azerbaijan (CBAR) from the official website (https://www.cbar.az/currencies/25.05.2022.xml) and used it in my project.
  
  
  ## Requirements

For building and running the application you need:

- [JDK 11]([http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html](https://www.oracle.com/eg/java/technologies/javase/jdk11-archive-downloads.html))
- [Gradle ]([https://maven.apache.org](https://gradle.org/install/))

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `de.codecentric.springbootsample.Application` class from your IDE.

### Run the project

```
docker-compose up
````

Containers for both services will be launched. The project can be reached at http://localhost:8000.

Hot-reloading is enabled (i.e. changes to the Java code in the project will cause the application to restart so that they 
can be used.)


### Some of the challenges i faced and features i hope to implement in the future.
1. By default PostgreSql cannot store LocalDate type directly in database by JPA. LocalDate type is stored as DATE type in PostgreSql
2. MapStruct was giving the error "Consider defining a bean of type 'az.example.rateofexchange.mapper.ValuteCursMapper' in your configuration".
I added  -> annotationProcessor "org.mapstruct:mapstruct-processor:1.5.3.Final" in build.gradle file
3. When using Mapstruct. I have a class that contains a Set<B> class inside A class and a Set<C> class inside it
var when I map this class using Mapstruct it doesn't generate the codes for C class. You can look inside the az.example.rateofexchange.mapper.ValuteCursMapper interface to see the solution to this

## The technology and libraries I use
Java, Spring Boot, Spring Security, PosgreSQL, Mapstruct, Apache Http Client, Javax Validation, Json web token, Springfox-swagger2, Liquibase, Lombok
