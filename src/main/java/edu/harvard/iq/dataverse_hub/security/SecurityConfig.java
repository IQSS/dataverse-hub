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
            .requestMatchers("/swagger-ui/**").permitAll() // Public endpoints
            .requestMatchers("/admin/**").hasRole("DVH_ADMIN") // Protected endpoints
            .anyRequest().authenticated() // All other endpoints
        ).build();
        
    }

}


// package edu.harvard.iq.dataverse_hub.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.annotation.Order;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


// @Configuration
// public class SecurityConfig {

//     @Autowired
//     private TokenAuthFilter tokenAuthFilter;

//     @Bean
//     @Order(2)
//     public SecurityFilterChain publicChain(HttpSecurity http) throws Exception {
//         System.out.println("SecurityConfig.filterChain");
//         return http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests((request) -> {
//                 request
//                     .requestMatchers("/openapi/**").permitAll() // Public endpoints
//                     .requestMatchers("/swagger-ui/**").permitAll();             
//                 }  
//             )
//             .build();        
//     }

//     @Bean
//     @Order(1)
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         System.out.println("SecurityConfig.filterChain");
//         return http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests((request) -> {
//                 request
//                     .requestMatchers("/api/**").permitAll()
//                     .anyRequest().permitAll();                               
//                 }  
//             )            
//             //.addFilterAfter(tokenAuthFilter, BasicAuthenticationFilter.class)            
//             .build();        
//     }

    



// }
