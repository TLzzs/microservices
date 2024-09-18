package com.ludistudy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class APIGateway {
    public static void main(String[] args) {
        SpringApplication.run(APIGateway.class, args);
    }
}