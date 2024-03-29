package com.spike.codegenerationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class CodeGenerationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeGenerationServiceApplication.class, args);
    }
}
