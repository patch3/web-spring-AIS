package org.example.ais.config.security;

import org.example.ais.config.constants.RoleConst;
import org.example.ais.services.CreditorDetailsServiceImpl;
import org.example.ais.services.client.ClientDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    @Lazy
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Lazy
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }

    @Bean
    public SecurityFilterChain standardFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        (request) -> request
                                .requestMatchers(
                                        //"/staff/**", "/client/**",
                                        "/styles/**",
                                        "/scripts/**",
                                        "/", "/home"
                                ).permitAll()
                                .anyRequest().authenticated()
                ).formLogin(
                        (form) -> form
                                .loginPage("/client/login/auth")
                );
        return http.build();
    }


    @Configuration
    @Order(1)
    public static class CreditorConfigurationAdapter {
        private final CreditorDetailsServiceImpl creditorDetailsService;
        private final PasswordEncoder passwordEncoder;

        @Autowired
        public CreditorConfigurationAdapter(
                CreditorDetailsServiceImpl creditorDetailsService,
                PasswordEncoder passwordEncoder
        ) {
            this.creditorDetailsService = creditorDetailsService;
            this.passwordEncoder = passwordEncoder;
        }

        @Bean
        public CommandLineRunner initData() {
            return args -> creditorDetailsService.register(BaseAdminConfig.EMAIL, BaseAdminConfig.PASSWORD);
        }

        @Autowired
        public void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
            managerBuilder.userDetailsService(creditorDetailsService).passwordEncoder(passwordEncoder);
        }

        @Bean
        public SecurityFilterChain creditorFilterChain(HttpSecurity http) throws Exception {
            http.securityMatcher("/staff/**")
                    .authorizeHttpRequests(
                            (requests) -> requests
                                    .requestMatchers(
                                            "/staff/login/auth",
                                            "/staff/login/auth/process"
                                    ).anonymous()
                                    .requestMatchers("/staff/**").hasRole(RoleConst.ADMIN)
                                    .anyRequest().authenticated()
                    ).formLogin(
                            (form) -> form
                                    .loginPage("/staff/login/auth")
                                    .loginProcessingUrl("/staff/login/auth/process")
                                    .failureUrl("/staff/login/auth?error")
                                    .defaultSuccessUrl("/staff/profile")
                    ).logout(
                            (logout) -> logout
                                    .logoutUrl("/staff/logout")
                                    .logoutSuccessUrl("/staff/login/auth?logout")
                                    .invalidateHttpSession(true)
                                    .deleteCookies("JSESSIONID")
                                    .permitAll()
                    ).csrf((csrf) -> csrf
                            //Customizer.withDefaults()
                            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    ).rememberMe((remember) -> remember
                            .rememberMeParameter("remember-me")
                            .key(SecretKeys.REMEMBER_ME)
                            .tokenValiditySeconds(SecretKeys.TIME_REMEMBER)
                            .userDetailsService(creditorDetailsService)
                    ).exceptionHandling(
                            (exception) -> exception
                                    .accessDeniedPage("/403")
                    );
            return http.build();
        }
    }

    @Configuration
    @Order(2)
    public static class ClientConfigurationAdapter {
        private final UserDetailsService clientDetailsService;
        private final PasswordEncoder passwordEncoder;

        @Autowired
        public ClientConfigurationAdapter(ClientDetailsServiceImpl clientDetailsService, PasswordEncoder passwordEncoder) {
            this.clientDetailsService = clientDetailsService;
            this.passwordEncoder = passwordEncoder;
        }

        @Autowired
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(clientDetailsService).passwordEncoder(passwordEncoder);
        }

        @Bean
        public SecurityFilterChain clientFilterChain(HttpSecurity http) throws Exception {
            http.securityMatcher("/client/**")
                    .authorizeHttpRequests(
                            (requests) -> requests
                                    .requestMatchers(
                                            "/client/login/reg", "/client/login/reg/process",
                                            "/client/login/auth", "/client/login/auth/process"
                                    ).anonymous()
                                    .requestMatchers("/client/**").hasRole(RoleConst.USER)
                                    .anyRequest().authenticated()
                    ).formLogin(
                            (form) -> form
                                    .loginPage("/client/login/auth").permitAll()
                                    .loginProcessingUrl("/client/login/auth/process")
                                    .failureUrl("/client/login/auth?error")
                                    .defaultSuccessUrl("/client/profile")
                    ).logout(
                            (logout) -> logout
                                    .permitAll()
                                    .logoutUrl("/client/logout")
                                    .logoutSuccessUrl("/login/auth?logout")
                                    .deleteCookies("JSESSIONID")
                                    .permitAll()
                    ).csrf(//Customizer.withDefaults()
                            (csrf) -> csrf
                                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    ).rememberMe(
                            (remember) -> remember
                                    .rememberMeParameter("remember-me")
                                    .key(SecretKeys.REMEMBER_ME)
                                    .tokenValiditySeconds(SecretKeys.TIME_REMEMBER)
                                    .userDetailsService(clientDetailsService)
                    ).exceptionHandling(
                            (exception) -> exception
                                    .accessDeniedPage("/403")
                    );
            return http.build();
        }
    }
}