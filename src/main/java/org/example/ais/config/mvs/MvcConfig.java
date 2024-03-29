package org.example.ais.config.mvs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*registry
                .addResourceHandler("/styles/**")
                .addResourceLocations("classpath:/static/styles/");*/

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /*registry.addViewController("/staff/logout").setViewName("redirect:/home?logout");
        registry.addViewController("/logout").setViewName("redirect:/home?logout");*/
    }
}
