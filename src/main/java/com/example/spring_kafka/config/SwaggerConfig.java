package com.example.spring_kafka.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springKafkaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Kafka Notification API")
                        .description("API for sending and receiving notifications via Kafka and Email")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Tea Pot")
                                .email("fake-email@mail.com")));
    }
}
