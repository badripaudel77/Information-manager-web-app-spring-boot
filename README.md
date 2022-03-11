## My Info
------------
### Information Keeping Web Application using Spring Boot, Data JPA, Thymeleaf and Spring  Security.

---------------------------------
### This repo contains code [without docker] uses localhost postgres and all the dependencies from local machine, switch to other branches to see Dockerfile and more about how to create, pull , push and run using docker container.
---------------------------------
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

## Deploy full stack Spring Boot application in elastic bean stalk [Amazon web services]

####  steps : 

- First create Relational Database Instance [ RDS instance]
   - From console create database
   - fill the details...
   - set spring profiles like application-profilename.properties and in config from IDE in run dropdown set --spring.profiles.active=profilename
   - set port to 5000 because elastic bean stalk needs it

  - Must add security bound rules.... from RDS instance clicking on security group
  
- Create Elastic Beanstalk
  - create environment and configure .... and done. 
  - now deploy the jar fire from target using maven install generated jar and it should be it. 

### Application has been deployed to AWS cloud [Note the link may not work in the future]

