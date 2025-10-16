**Java Spring Boot, Kafka working with entity User and Account**

### !!! kafka - consumer !!!

When deleting or creating a user, the application must send a message to kafka,
which contains information about the operation and the user’s email.

*work with message:*

``docker exec -it kafka bash``

``kafka-console-consumer --bootstrap-server localhost:9092 --topic user.notifications --from-beginning``


Thanks to this project it was possible to learn:

- realized OneToMany Bidirectional relationship
- work with REST API
- Spring Data JPA
- CRUD for User and Account
- PostgreSQL
- Kafka
- test containers




