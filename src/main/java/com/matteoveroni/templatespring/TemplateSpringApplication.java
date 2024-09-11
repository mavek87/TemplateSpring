package com.matteoveroni.templatespring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TemplateSpringApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TemplateSpringApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("Hello world from commandLine runner!!!");
    }
}