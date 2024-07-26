package com.example.spring_boot_demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Spring Boot 3 Rest Api demo",
                version = "0.2",
                description = "My DEMO API",
                license = @License(name = "MIT", url = "https://github.com/alankylee/spring-boot-rest-api-demo/blob/main/LICENSE"),
                contact = @Contact(url = "https://github.com/alankylee", name = "Alan Lee", email = "lky0201@hotmail.com")
        )
)
@SpringBootApplication
@EnableScheduling
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
