package com.codedifferently.CD_InternTracker.authentication;

import com.codedifferently.CD_InternTracker.models.TA;
import com.codedifferently.CD_InternTracker.services.TAServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final AuthFilter authFilter;
    private TAServiceImpl TAService;

    public SecurityConfig(AuthFilter authFilter, TAServiceImpl TAService) {
        this.authFilter = authFilter;
        this.TAService = TAService;  // only used to seed users, removable later
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/intern/**").authenticated()
                        .requestMatchers("/api/user/**").authenticated()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(form -> form.disable())
                .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager();
    }
    @PostConstruct
    public void seedAdminUser() {

            TA adminUser = new TA("admin123", "admin@example.com", "1234567890", "Michael Womer", "NA", true, true);
            TAService.create(adminUser);
            System.out.println("Test admin seeded");
            System.out.println("email: admin@example.com");
            System.out.println("password: admin123");
        }
    @PostConstruct
    public void seedUser() {

        TA user = new TA("user123", "user@example.com", "1234567890", "Michael Womer", "NA", false, true);
        TAService.create(user);
        System.out.println("Test user seeded");
        System.out.println("email: user@example.com");
        System.out.println("password: user123");
    }
}