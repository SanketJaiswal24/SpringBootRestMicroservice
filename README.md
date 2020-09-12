# UserRecord REST API ![CI Status](https://travis-ci.org/albanoj2/order-rest-backend.svg?branch=master)

Demonstrates a simple RESTful web service using Spring Boot and Java.This web service provides an in-memory UserRecord service, with the capability to get a all User information in the System, create user info,update user info, delete user info,user can be singIn in system,and logIn in system. After exploring this project, We will understand the three common layers of a RESTful web service (namely domain, data source, and presentation), the common design decisions used when a creating a web service, and the functionality provided by the Spring framework for creating a web service (as well as supplemental functionality, such as creating [HATEOAS](http://projects.spring.io/spring-hateoas/) links). In particular,We can aslo create OpenAPI Documantion with [Swagger](https://swagger.io/) links).And expose the following API Endpoints. 

 - We can create a domain model with the help of [lombok](https://github.com/mplushnikov/lombok-intellij-plugin) this easy beacuse we can reduce the boilerplate code. 
 - We can store domain objects in a persistence layer with the help of [JPA](https://spring.io/projects/spring-data-jpa)
 - We can wrap domain objects within resource objects.
 - We can add [HATEOAS](http://projects.spring.io/spring-hateoas/) links) links to a resource.Beacuse we can create self   discoverable API.
 - We can serve up resources to a client over HTTP
 - We can provide RESTfull CreateUser,UpdateUser,DeleteUser,SearchUser,LoginUser,SingUpUser,AllUser this operations to change domain objects.
 - We can create unit,integration with Individual Layer Controller,Service,Repository that exercise a REST API. this test create with the help of [Mockito](https://site.mockito.org/) & [Junit5](https://junit.org/junit5/).
 - We can Document Your API Enpoint With [Swagger](https://swagger.io/).
 - We can expose your API Endpoint with [Swagger](https://swagger.io/) & [Postman](https://www.getpostman.com/).
 - We can Version Your API to maintain forward and backward compatibality [Best practices for API versioning](https://stackoverflow.com/questions/389169/best-practices-for-api-versioning).
 - We can determine your [CodeCoverage](https://en.wikipedia.org/wiki/Code_coverage).
 - We can Configure your In-Memory Database [apacheDerby](https://db.apache.org/derby/).
 - We can debug your In-Memory Database console.
 - We can also understand slicing different components in UnitTest.
 - We can also profiling & Seprate cross cutting concerns the whole project with the help pf [AspectJ](https://docs.spring.io/spring/docs/3.0.0.M3/reference/html/ch08s08.html).

## Starting the UserRecord REST API
To start this web service,We can use [SpringInitializr](https://start.spring.io/) to build your all dependency in Maven Project.
    
Once the web service is started, it can be reached at

    http://localhost:8080

## REST Endpoints
The following REST endpoints are available upon deployment of the order management system:

| HTTP Verb        | URL           | Description  | Status Codes |
| ------------- |-------------|:-----| ----|
| `GET` | `http://localhost:8080/user/api/show_all_users/v1.0` | Obtains a list of all existing User | <ul><li>`200 OK`</li></ul> |
| `POST` | `http://localhost:8080/user/api/search_user/v1.0` | Obtains the User corresponding to the supplied User ID | <ul><li>`200 OK` if User exists</li><li>`404 Not Found` if User does not exist</li></ul> |
| `POST` | `http://localhost:8090/user/api/create_user/v1.0` | Creates a new User based on the payload contained in the request body | <ul><li>`201 Created` if User successfully created</li></ul> |
| `PUT` | `http://localhost:8080/user/api/updateuser/v1.0` | Updated an existing User with the data contained in the request body | <ul><li>`200 OK` if User succesfully updated</li><li>`404 Not Found` if User does not exist</li></ul> |
| `DELETE` | `http://localhost:8080/user/api/deleteuser/v1.0` | Deletes an existing User that corresponds to the supplied User ID | <ul><li>`203 No Content` if User succesfully deleted</li><li>`404 Not Found` if User does not exist</li></ul> |
| `POST` | `http://localhost:8080/user/api/singin/v1.0` | User can be singUp with credentials in payload | <ul><li>`201 Created` if User successfully singUp</li></ul> |
| `POST` | `http://localhost:8080/user/api/login/v1.0` | User can be Login with credentials in payload | <ul><li>`200 OK` if User successfully login</li></ul> |
