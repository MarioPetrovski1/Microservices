# Microservices with Spring

This repository is a microservice architecture build with Spring.
There are a couple of microservices:
* Customer
* Notification
* Fraud

Service discovery is with netflix eureka server.
For distributed logging there is spring cloud sleuth and Zipkin.
All microservices are using postgresql database.

There is a api gateway that routes the requests to the customer microservice.

Customer microservice communicates with fraud over HTTP protocol and with Notification
over RabbitMQ ( Customer produces a message and Notification gets it and processes it ).


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

* Java JDK 8 or higer
* PostgreSQL
* Docker

### Installing

* Clone repo
* Import the project in any IDE

### Build and Start the project

First change in the root pom.xml the execution of JIB from build to dockerBuild because build will build the images and then push them to docker hub.If you want to use it this way you have to change the configuration to point to your docker hub account,which currently is set to mine. (dockerBuild will build the images locallly and wont try to push them to docker hub)
Go to the root of the folder and run mvn clean package.
This will build all the jars for all microservices.
To get them all up and running we need to follow this order:

First we run a docker container for PostgreSQL and Zipkin. ( configuration is provided in docker-compose.yml )
Then we start the eureka server.
Then we start all the microservices ( apigateway,customer,fraud,notification)

If you want to get them up and running in a docker environment go to the root folder and run docker compose up.

Then we can execute POST HTTP requests to the api gateway which is listening on port 8084
URL : http://localhost:8084/api/v1/customers/
Method TYPE: POST

Body : 
{
	"firstName":"NAME",
	"lastName":"LASTNAME",
	"email":"email@email.com"
}


## Built With

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Cloud](https://spring.io/projects/spring-cloud)
* [JIB](https://github.com/GoogleContainerTools/jib)
* [RabbitMQ](https://www.rabbitmq.com/)
* [Zipkin](https://zipkin.io/)