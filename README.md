## Explanation and Information

#### Note : For detail explanation of how to dockerize simple spring boot Java application you can visit my website at [guides to code ](https://guidestocode.com/java/how-to-dockerize-java-spring-boot-application/)
------------
### Information Keeping Web Application using Spring Boot, Data JPA, Thymeleaf and Spring  Security.

#### 'Uses Docker Image available at docker hub link below and postgres official docker image to run the application with docker-compose'

#### To test use command  _**```docker pull badripaudel77/info-keeper-spring-boot-docker:my-info-app```**_

#### and to run, type the command _**```docker run -p 8080:8080 badripaudel77/info-keeper-spring-boot-docker:my-info-app```**_  and hit ```localhost:8080``` in browser and you should good to see the result. 

### This branch will use docker compose to create image, run as application wants to run with database docker image.

#### For reference : [Docker compose at Baeldung](https://www.baeldung.com/ops/docker-compose)
------------------
------------------
#### Uses MVC pattern

##### Dependency Used : 
                      Spring Web, Hibernate, Data JPA, Thymeleaf, Security, PostgresSQL
                      
----------------------------------------------------------------------
#### Project file Structure

- java -> All the source codes
- static -> All CSS, JS & Images and all static files if any
- templates -> All HTML files [thymeleaf]
- application.properties -> all application properties
- pom.xml file -> Includes all the dependencies required 
----------------------------------------------------------------------

MVC
-------- 
##### Model [M] -> User and Contact [ One User have many contacts]

##### View [V] -> View pages [.html] files in our cases

##### Controller [C] -> Holds all the required business logic

---------------------------------------------------------------

### Run Using docker [for APP using docker-compose]
```docker-compose up```

---------------------------------------------------------------

## Deploy full stack Spring Boot application in elastic bean stalk [checkout to local branch]

### Actuator
- To access actuator endpoint, hit /actuator and you'll see all the available endpoints. By default, only /actuator/health is enabled.
- To get all other endpoints, include "management.endpoints.web.exposure.include=*" in application.properties file.
- For example, now you can access: /actuator/metrics, it will give all the metrics information which you can
  use to see additional information. Like copy name displayed in /metrics and append that as : metrics/hikaricp.connections
- This will give hikari connection pool info like total no of connections. And other ppts will give other important information.

                      
