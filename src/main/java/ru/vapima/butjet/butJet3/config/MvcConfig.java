package ru.vapima.butjet.butJet3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/","/app/hello");
        registry.addRedirectViewController("/app","/app/hello");
        registry.addRedirectViewController("/app/","/app/hello");
        registry.addViewController("/app/about").setViewName("about");
        registry.addViewController("/app/login").setViewName("login");
    }

}