package org.example.ais;

import org.example.ais.config.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class WebSpringAisApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSpringAisApplication.class, args);
    }

}
