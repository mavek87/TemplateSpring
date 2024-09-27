package com.matteoveroni.templatespring.configurations;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class DataSourceConfigProd {

    @Primary
    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://postgres-app:5432/app_db")
                .username("postgres")
                .password("password")
                .build();
    }
}