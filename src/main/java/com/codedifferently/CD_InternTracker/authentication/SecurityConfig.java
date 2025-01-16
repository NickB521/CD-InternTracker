package com.codedifferently.CD_InternTracker.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
    //this is to temporarily allow http requests without a password, will be removed later
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()); //allows post requests without special authentication, will remove when better solution is found
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); //allows all requests without authentication, will remove when roles are defined.

        return http.build();
    }
}