package org.example.contosositemaven.config.mvs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // страницы для юсеров
        registry.addViewController("/home").setViewName("/home");
        registry.addViewController("/").setViewName("/home");
        registry.addViewController("/login/auth").setViewName("/login/auth");
        registry.addViewController("/profile").setViewName("/profile");

        // страница для сотрудников
        registry.addViewController("/staff/login/auth").setViewName("/login/auth");
    }
}
