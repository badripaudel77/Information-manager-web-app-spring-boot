## Explanation and Information

------------
### Information Keeping Web Application using Spring Boot, Data JPA, Thymeleaf and Spring  Security.

#### Docker Image is available at : [Docker Hub Badri Paudel](https://hub.docker.com/repository/docker/badripaudel77/info-keeper-spring-boot-docker) One can pull the image

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

## Deploy full stack Spring Boot application in elastic bean stalk

####  steps : 

- First create Relational Database Instance [ RDS instance]
   - From console create database
   - fill the details...
   - set spring profiles like application-profilename.properties and in config from IDE in run dropdow set --spring.profiles.active=profilename
   - set port to 5000 because elastic bean stalk needs it

  - Must add security bound rules.... from RDS instance clicking on security group
  
- Create Elastic Beanstalk
  - create environment and configure .... and done. 
  - now deploy the jar fire from target using maven install generated jar and it should be it. 

### Application has been deployed to AWS cloud

                      
