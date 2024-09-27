package com.matteoveroni.templatespring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableJpaRepositories
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@Slf4j
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        log.info("Java {} {}", System.getProperty("java.vendor"), System.getProperty("java.version"));
        log.info("Environment: {}", System.getenv("APP_ENVIRONMENT"));
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("Application started");
    }
}