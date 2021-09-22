package com.infy.ekart.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport{

	
	@Bean
    public Docket testApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.infy.ekart.api"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }
	
	private ApiInfo metaData() {
        return new ApiInfo(
                "EKart Spring Boot REST API",
                "EKart Spring Boot REST API for FA4",
                "1.0",
                "Terms of service",
                new Contact("JAVA FA4", "https://infosys.com", "animesh_rai@infosys.com"),
                "ExpApp License Version 1.0",
                "https://infosys.com",
                Collections.emptyList()
        );
    }
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

            registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");

            registry.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
	
}
