package ru.vapima.butjet.butJet3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.security.Security;
import java.util.Arrays;
import java.util.List;


@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(HttpAuthenticationScheme
                        .JWT_BEARER_BUILDER
                        .name("JWT")
                        .build()))
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("ru.vapima.butjet.butJet3.controllers.api.impl"))
                .build();
    }



    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(List.of(defaultAuth()))
                .operationSelector(o -> o.requestMappingPattern().matches("/.*"))
                .build();
    }

    private SecurityReference defaultAuth() {
        return SecurityReference.builder()
                .scopes(new AuthorizationScope[0])
                .reference("JWT")
                .build();
    }


    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("ButJet API")
                .title("ButJet serever")
                .termsOfServiceUrl("http://vapima.ru/butjet")
                .contact(new Contact("Vasily","Http://vapima.ru","vapima@gmail.com"))
                .licenseUrl("http://vapima.ru/butjet/license")
                .version("0.3")
                .build();
    }

}