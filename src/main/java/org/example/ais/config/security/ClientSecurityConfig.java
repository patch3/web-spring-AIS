package org.example.ais.config.security;

import org.example.ais.services.client.ClientDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Order(1)
public class ClientSecurityConfig {
    private final UserDetailsService clientDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClientSecurityConfig(ClientDetailsServiceImpl clientDetailsService, PasswordEncoder passwordEncoder) {
        this.clientDetailsService = clientDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(clientDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain clientFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                    .requestMatchers(
                            "/login/auth",
                            "/login/reg",
                            "/login/reg/*"
                    ).anonymous()
                    .requestMatchers(
                            "/profile"
                    ).authenticated()
            ).formLogin((form) -> form
                    .loginPage("/login/auth").permitAll()
                    .defaultSuccessUrl("/profile")
                    .loginProcessingUrl("/login/auth/process")
                    .permitAll()
            );
        return http.build();
    }
}
