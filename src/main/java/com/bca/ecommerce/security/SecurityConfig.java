package com.bca.ecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable()) // OK for BCA project

            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/login",
                        "/register",
                        "/perform_register",
                        "/perform_login", // ⭐ VERY IMPORTANT
                        "/css/**",
                        "/js/**",
                        "/images/**"
                ).permitAll()
                .requestMatchers("/WEB-INF/**").permitAll()	
                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login")                 // your custom login
                .loginProcessingUrl("/perform_login") // must match form action
                .defaultSuccessUrl("/user/products", true)
                .failureUrl("/login?error=")
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
            )
            .sessionManagement(session -> session
                    .maximumSessions(1)                 // only one login allowed
                    .maxSessionsPreventsLogin(false)    // kicks old login if new login happens
            );

        return http.build();
    }

}
