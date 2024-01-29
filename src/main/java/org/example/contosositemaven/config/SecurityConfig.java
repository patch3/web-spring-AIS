package org.example.contosositemaven.config;

import org.example.contosositemaven.services.ClientDetails.ClientDetailsService;
import org.example.contosositemaven.services.ClientDetails.ClientServiceImpl;
import org.example.contosositemaven.services.CreditorDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final ClientDetailsService clientDetailsService;

    private final CreditorDetailsServiceImpl creditorDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(ClientServiceImpl clientDetailsService, CreditorDetailsServiceImpl creditorDetailsService, PasswordEncoder passwordEncoder) {
        this.clientDetailsService = clientDetailsService;
        this.creditorDetailsService = creditorDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers(
                        "/login/auth",
                        "/login/reg",
                        "/login/auth/admin",
                        "/login/reg/process").anonymous()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login/auth")
                .loginProcessingUrl("/login/auth/process")
                .defaultSuccessUrl("/home")
                .successForwardUrl("/home")
                .failureUrl("/login/auth?error=true")
                .and()
            .formLogin()
                .loginPage("/login/auth/admin")
                .loginProcessingUrl("/login/auth/admin/process")
                .defaultSuccessUrl("/home")
                .successForwardUrl("/home")
                .failureUrl("/login/auth/admin?error=true")
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .and()
            .exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(clientDetailsService).passwordEncoder(passwordEncoder);
        auth.userDetailsService(creditorDetailsService).passwordEncoder(passwordEncoder);
    }
}*/
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final ClientDetailsService clientDetailsService;

    private final CreditorDetailsServiceImpl creditorDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(ClientServiceImpl clientDetailsService, CreditorDetailsServiceImpl creditorDetailsService, PasswordEncoder passwordEncoder) {
        this.clientDetailsService = clientDetailsService;
        this.creditorDetailsService = creditorDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(Customizer.withDefaults())
            .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/", "/home").permitAll()
                    .requestMatchers(
                            "/login/reg","/login/profile",
                            "/login/auth"
                    ).anonymous()
                    .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                    .loginPage("/login/auth")
                    .permitAll()
                    .defaultSuccessUrl("/login/profile")
            )
            .logout((logout) -> logout
                    .logoutSuccessUrl("/logout")
                    .permitAll()
            );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(clientDetailsService).passwordEncoder(passwordEncoder);
        auth.userDetailsService(creditorDetailsService).passwordEncoder(passwordEncoder);
    }
}