package edu.harvard.iq.dataverse_hub.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        return http
        .authorizeHttpRequests((requests) -> requests
            .requestMatchers("/api/**").permitAll() // Public endpoints
            .requestMatchers("/openapi/**").permitAll() // Public endpoints
            .requestMatchers("/admin/**").hasRole("DVHUB_ADMIN") // Protected endpoints
            .anyRequest().authenticated() // All other endpoints
        ).build();
        
    }

}
