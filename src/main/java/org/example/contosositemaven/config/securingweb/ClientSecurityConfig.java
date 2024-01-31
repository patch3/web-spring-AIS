package org.example.contosositemaven.config.securingweb;

import org.example.contosositemaven.services.ClientDetailsServiceImpl;
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
                    .requestMatchers("/login/auth").anonymous()
                    .requestMatchers("/login/auth","/login/reg","/login/reg/*").anonymous()
                    .anyRequest().authenticated()
            ).formLogin((form) -> form
                    .loginPage("/login/auth").permitAll()
                    .defaultSuccessUrl("/profile")
                    .permitAll()
            ).logout((logout) -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .permitAll()
            ).exceptionHandling()
                .accessDeniedPage("/access-denied");
        return http.build();
    }
}
