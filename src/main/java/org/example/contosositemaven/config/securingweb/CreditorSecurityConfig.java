package org.example.contosositemaven.config.securingweb;

import org.example.contosositemaven.services.ClientDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class CreditorSecurityConfig {
    private final UserDetailsService creditorDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CreditorSecurityConfig(ClientDetailsServiceImpl creditorDetailsService, PasswordEncoder passwordEncoder) {
        this.creditorDetailsService = creditorDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(creditorDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain
}
