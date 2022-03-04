## Explanation and Information

------------
### Information Keeping Web Application using Spring Boot, Data JPA, Thymeleaf and Spring  Security.

#### Docker Image is available at : [Docker Hub Badri Paudel](https://hub.docker.com/repository/docker/badripaudel77/info-keeper-spring-boot-docker) One can pull the image to #### test using _**docker pull badripaudel77/info-keeper-spring-boot-docker:my-info-app**_ and run using docker run command _**docker run -p 8080:8080 badripaudel77/info-keeper-spring-boot-docker:my-info-app**_ hit localhost:8080 and you should good to see the result. 
### In case you get connection error from postgress, make sure you've allowed that added IP in application.properties file to pg_hba.conf in IPV4 Address section
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

### Run Using docker
- **Docker build :**  _**docker build --tag spring-docker .**_

- **Run Docker Image :** _**docker run -p 8080:8080 spring-docker**_

---------------------------------------------------------------

## Deploy full stack Spring Boot application in elastic bean stalk

####  steps : 

- First create Relational Database Instance [ RDS instance]
   - From console create database
   - fill the details...
   - set spring profiles like application-profilename.properties and in config from IDE in run dropdow set --spring.profiles.active=profilename
   - set port to 5000 because elastic bean stalk needs it

  - Must add security bound rules.... from RDS instance clicking on security group
  
- Create Elastci Beanstalk
  - create enviroment and configure .... and done. 
  - now deploy the jar fire from target using maven install generated jar and it should be it. 

### Application has been deployed to AWS cloud

                      
