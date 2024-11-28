package com.axiohelix.gymmanagement.security;

//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class WebSecurityConfig {
//
//    private static final String[] WHITE_LIST_URL = { "/swagger-ui/**","/v3/api-docs/**" ,"/v2/api-docs/**", "/swagger-resources/**", "/webjars/**" ,"/api/v1","/auth/login" };
//
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//    private final CustomUserdetailsService customUserDetailsService;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(cors -> cors.disable())                 // Disable CORS
//                .csrf(csrf -> csrf.disable())                 // Disable CSRF
//                .sessionManagement(session -> session.disable()) // Disable session management
//                .formLogin(form -> form.disable())            // Disable form login
//                .authorizeHttpRequests(auth -> auth
//                                .requestMatchers("/api/v1").permitAll()        // Allow access to root URL
//                                .requestMatchers("/auth/login").permitAll() // Allow access to login endpoint
//                                .requestMatchers(HttpMethod.GET,WHITE_LIST_URL ).permitAll() // Allow Swagger UI access
//                                .anyRequest().authenticated()            // Authenticate all other requests
//                )
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//
//    public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .userDetailsService(customUserDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//}

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    // Define the Swagger whitelist URLs
    private static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/api-docs/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**"
    };

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserdetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.disable())                 // Disable CORS
                .csrf(csrf -> csrf.disable())                 // Disable CSRF
                .sessionManagement(session -> session.disable()) // Disable session management
                .formLogin(form -> form.disable())            // Disable form login
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(SWAGGER_WHITELIST).permitAll() // Allow access to Swagger endpoints
//                        .requestMatchers("/auth/login").permitAll() // Allow access to login endpoint
//                        .requestMatchers(HttpMethod.GET, "/api/v1").permitAll() // Allow access to root URL
                                .requestMatchers(SWAGGER_WHITELIST).permitAll() // Allow access to Swagger resources
                                .requestMatchers("/auth/login").permitAll()     // Allow access to login endpoint
                                .requestMatchers(HttpMethod.GET, "/api/v1").permitAll() // Allow access to root URL
                        .anyRequest().authenticated()            // Authenticate all other requests
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
