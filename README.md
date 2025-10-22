**Spring Boot service working with entity NotificationEvent**

Notification Service is a separate microservice for processing events from Kafka and sending email notifications.
It listens for messages from the same topic and generates emails depending on the event type (USER_CREATED, USER_DELETED)

HATEOAS implemented using ModelAssembler (a separate assembler class for generating models with links).

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
    "help": { "href": "http://localhost:8081/notification/help" }  
  }  
}  
```
*user service:* http://localhost:8080  
*notification service:* http://localhost:8081  
*swagger:* http://localhost:8081/swagger-ui.html  
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




