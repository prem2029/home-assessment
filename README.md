# Spring Boot Application /Home Assessment

## Assessment 
Specification: Create a RESTful application using Java and Spring Boot. It should have an API supporting the basic CRUD operations for products:  
+ Create a new product 
+ Retrieve a list of all products  
+ Update a product
+ Delete a product (soft deletion) Each product should have a SKU (unique id), a name, a price and date it was created. 
Order (optional) 


Let’s introduce the concept of order, meaning that your API should also support:  
+ Placing an order 
+ Retrieving all orders within a given time period  


Each order should have a list of product, unique id, the buyer’s e-mail, and the time the order was placed. It should be possible to calculate the total order amount, based on the price of the individual products.

## Requirements
For building and running the application you need:

+ JDK 1.8

+ Gradle 4.8.1

+ SpringBoot 2.0.6 RELEASE

+ Mongo 4.0



## External Tools Used
+ Postman - API Development Environment (Testing Docmentation)
+ STS IDE

## Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.home.assessment.HomeAssessmentApplication class from your IDE.

+ Download the zip or clone the Git repository.
+ Unzip the zip file (if you downloaded one)
+ Open Command Prompt and Change directory (cd) to folder containing pom.xml
+ Open STS IDE
  + File -> Import -> Existing Gradle Project -> Navigate to the folder where you unzipped the zip
  + Select the project
+ Database Configuration in the application properties file (home-assessment\src\main\resources\application-test.properties)
+ Right Click on the Project "home-assessment" and Run as Spring Boot App

## Project Structure


|  Module |  Remarks |
|----------|--------------|
|`home-assessment`                       | Contains RestController, Database & Swagger Configuration, and Test Classess |
|`home-assessment-service`                       | Contains Service class and Business logic  |
|`home-assessment-model`                       |  Contains Mongo Collection and Pojo Classess|
|`home-assessment-repository`                       | Contains Repository Clasess to execute the DB queries |


Test on the browser via SWAGGER
-------------------

```sh
http://localhost:8888/home-assessment/swagger-ui.html
```

## Generate War
+ Execute the below command in the root project folder

./gradlew bootwar

### URLs

|  URL |  Method | Remarks |
|----------|--------------|--------------|
|`http://localhost:8888/home-assessment/product/v1/list`                       | GET | |
|`http://localhost:8888/home-assessment/product/v1/create`                       | POST | |
|`http://localhost:8888/home-assessment/product/v1/update`                       | PUT | |
|`http://localhost:8888/home-assessment/product/v1/delete`                       | DELETE | |
|`http://localhost:8888/home-assessment/order/v1/create`                 | POST | |
|`http://localhost:8888/home-assessment/order/v1/getOrdersByDateRange`                 | POST | |


