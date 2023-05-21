package com.hamitmizrak.security;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// LOMBOK
@RequiredArgsConstructor

// SECURITY
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    @SneakyThrows // throws Exception
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        //http.httpBasic(); //http düzeyinde
        http.formLogin(); // Form sayfası düzeyinde

        http.authorizeHttpRequests()
                .requestMatchers("/","/index").permitAll() // index izin ver
                .requestMatchers("/email/api/v1/basic/email").permitAll(); // Email Göndermeye izin ver
        return http.build();
    } //end securityFilterChain

} //end class
