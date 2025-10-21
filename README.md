**Java Spring Boot, Kafka working with entity NotificationEvent**

The service listens to Kafka events about user actions and sends email notifications via EmailService.

*work with message:*

``docker exec -it kafka bash``

``kafka-console-consumer --bootstrap-server localhost:9092 --topic user.notifications --from-beginning``

*swagger:* http://localhost:8081/swagger-ui.html

HATEOAS implemented using `ModelAssembler` (a separate assembler class for generating models with links).

Thanks to this project it was possible to learn:

- realized OneToMany Bidirectional relationship
- work with REST API
- Spring Data JPA
- CRUD for User and Account
- PostgreSQL
- Kafka
- test containers
- swagger
- HATEOAS




