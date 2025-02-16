## Ideas, Explanations for Information Keeping Web Application using Spring Boot, Data JPA, Thymeleaf, Spring  Security.
______________________________________________________________

1. Uses MVC pattern as an architectural design pattern.
### MVC
______________________________________________________________
-> Model [M] -> User and Contact [ One User have many contacts]

-> View [V] -> View pages [.html] files in our cases

-> Controller [C] -> Holds all the required business logic (service logic will be handled by services but will be used in controller)

2. #### Project file Structure

->  java -> All the source codes
-> static -> All CSS, JS & Images and all static files if any
-> templates -> All HTML files [thymeleaf]
-> application.properties -> all application properties (contains environment wise properties like for local and production with profiling activation)
-> pom.xml file -> Includes all the dependencies required 
______________________________________________________________

##### Dependency Used : 
                      Spring Web, Hibernate, Data JPA, Thymeleaf, Security, PostgresSQL, Apache POI, OpenCSV

______________________________________________________________

### What is project about and what it contains
______________________________________________________________
1. User can register and use the application. By default they will be registered as normal user without admin previleges.
2. Admin user will have additional reserved endpoints where they can perform certain action when they login.
3. User can add information, for example: add notes/contact information with few text fields and an image field.
4. User will have navigation options when there are large number of entries.
5. User can reset/update passwords
6. User can import contacts from CSV file and application will store it into the database.
7. Additionally, user can also export the contact list in the csv/excel file.


### Architecture
______________________________________________________________
1. When code gets pushed to github, github action will trigger that will actually run unit tests using maven, build it, create the docker image and push it to the dockerhub.
______________________________________________________________

## Deploy full stack Spring Boot application in elastic bean stalk [checkout to local branch]

### Actuator
- To access actuator endpoint, hit /actuator and you'll see all the available endpoints. By default, only /actuator/health is enabled.
- To get all other endpoints, include "management.endpoints.web.exposure.include=*" in application.properties file.
- For example, now you can access: /actuator/metrics, it will give all the metrics information which you can
  use to see additional information. Like copy name displayed in /metrics and append that as : metrics/hikaricp.connections
- This will give hikari connection pool info like total no of connections. And other ppts will give other important information.

______________________________________________________________

### How to use:
- 'Uses Docker Image available at docker hub link below and postgres official docker image to run the application with docker-compose'

- To test use command  _**```docker pull badripaudel77/info-keeper-spring-boot-docker:my-info-app```**_

- And to run, type the command _**```docker run -p 8080:8080 badripaudel77/info-keeper-spring-boot-docker:my-info-app```**_  and hit ```localhost:8080``` in browser and you should good to see the result. 

- **NOTE:** Sometimes, to build image (caching effect will not show the recent changes sometimes)
```bash
   docker-compose up --build 
```

### Run Using docker [for APP using docker-compose]
```docker-compose up```

#### Note : For detail explanation of how to dockerize simple spring boot Java application you can visit my website at [guides to code ](https://medium.com/@badripaudel77/dockerize-java-spring-boot-application-in-easy-steps-e15e970b9098)

- This branch will use docker compose to create image, run as application wants to run with database docker image.

- For reference : [Docker compose at Baeldung](https://www.baeldung.com/ops/docker-compose)

                      
