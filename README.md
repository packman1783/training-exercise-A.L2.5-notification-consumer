**Spring Boot service working with entity NotificationEvent**

Notification Service is a separate microservice for processing events from Kafka and sending email notifications.
It listens for messages from the same topic and generates emails depending on the event type USER_CREATED, USER_DELETED  
(Another part of service after creating or deleting user, the service generates NotificationEvent and sends it to the user.notifications Kafka topic)

HATEOAS is implemented in this service to enrich responses with self-descriptive links.

*example request:* 
```
{  
  "email": "user@mail.com",  
  "subject": "Hello",  
  "body": "Manual email test"  
}
```
*example response:*  
```
{  
  "message": "Email sent to user@mail.com",  
  "_links": {  
    "self": { "href": "http://localhost:8081/notification/send" },  
  }  
}  
```
*user service:* http://localhost:8080  
*notification service:* http://localhost:8081  
*swagger:* http://localhost:8081/swagger-ui.html or src/main/resources/static/openapi.json  
*mailhog:* http://localhost:8025

Thanks to this project it was possible to learn:  
- realized OneToMany Bidirectional relationship
- work with REST API
- Spring Data JPA
- CRUD for User and Account
- PostgreSQL
- Spring Boot
- Kafka
- test containers
- swagger
- HATEOAS




