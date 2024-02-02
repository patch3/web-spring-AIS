package org.example.ais.config.security;

import org.example.ais.config.constants.RoleConst;
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
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@Order(2)
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
    public SecurityFilterChain creditorFilterChain(
            HttpSecurity http,
            HandlerMappingIntrospector introspector
    ) throws Exception {
        MvcRequestMatcher.Builder mvcMatherBuilder = new MvcRequestMatcher.Builder(introspector);
        http
            .securityMatcher("/staff/**")
            .authorizeHttpRequests((request) -> request
                    .requestMatchers(
                            "/staff/login/auth/*",
                            "/staff/login/auth/"
                    ).anonymous()
                    .requestMatchers(mvcMatherBuilder.pattern("/staff/**")).hasRole(RoleConst.USER)
            ).formLogin((form) -> form
                 .loginPage("/login/auth/").permitAll()
                 .defaultSuccessUrl("/profile")
                 .permitAll()
            );
        return http.build();
    }


/*
    @Bean
    public SecurityFilterChain*/
}
