package com.exchange.security;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig{
    @Value("${jwt.username}")
    private String sampleUsername;
    @Value("${jwt.password}")
    private String samplePassword ;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtRequestFilter jwtRequestFilter) throws Exception {

        return http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/login").permitAll() // Allow login without authentication
                        .requestMatchers("/api/calculate").authenticated() // Protect API endpoint
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername(sampleUsername)
                        .password(passwordEncoder().encode(samplePassword)) // Encoding password
                        .roles("USER")
                        .build()
        );
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
