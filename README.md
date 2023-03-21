# Rate of Exchange

Hello. In this project, I took the official daily exchange rate of Central Bank of the Republic of Azerbaijan (CBAR) from the official website (https://www.cbar.az/currencies/25.05.2022.xml) and used it in my project.



## Some of the challenges i faced and features i hope to implement in the future.
1. By default PostgreSql cannot store LocalDate type directly in database by JPA. LocalDate type is stored as DATE type in PostgreSql
2. MapStruct was giving the error "Consider defining a bean of type 'az.example.rateofexchange.mapper.ValuteCursMapper' in your configuration".
I added  -> annotationProcessor "org.mapstruct:mapstruct-processor:1.5.3.Final" in build.gradle file
3. When using Mapstruct. I have a class that contains a Set<B> class inside A class and a Set<C> class inside it
var when I map this class using Mapstruct it doesn't generate the codes for C class. You can look inside the az.example.rateofexchange.mapper.ValuteCursMapper interface to see the solution to this



## The technology and libraries I use
Java, Spring Boot, Spring Security, PosgreSQL, Mapstruct, Apache Http Client, Javax Validation, Json web token, Springfox-swagger2, Liquibase, Lombok
