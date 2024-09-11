package com.matteoveroni.templatespring.configurations;

import com.matteoveroni.templatespring.domain.mappers.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(config -> {
                    config.disable();
                })
                .authorizeHttpRequests(auth -> {
                    auth.anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    public UserMapper getUserMapper() {
        return UserMapper.INSTANCE;
    }

}

//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//@Configuration
//public class SecurityConfig {
//
////    @Bean
////    @ConditionalOnMissingBean(UserDetailsService.class)
////    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
////        UserDetails user = User.withUsername("user")
////                .password("{noop}password").roles("USER")
////                .build();
////        UserDetails admin = User.withUsername("admin")
////                .password("{noop}password").roles("ADMIN")
////                .build();
////        return new InMemoryUserDetailsManager(user, admin);
////    }
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        return http
////                .authorizeHttpRequests(config -> {
////                    config.requestMatchers("login").permitAll();
////                })
////                .build();
////    }
//}
