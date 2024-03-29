package com.gestionstock.Gestion.de.stock.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.gestionstock.Gestion.de.stock.utils.Constants.APP_ROOT;

@Configuration
//@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                   new ApiInfoBuilder()
                     .description("Gestion de stock API documentation")
                     .title("gestion de stock REST V1")
                     .build()
                )
                .groupName("REST API V1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gestionstock.Gestion.de.stock"))
                .paths(PathSelectors.ant(APP_ROOT + "/**"))
                //.paths(PathSelectors.ant(APP_ROOT +"/**"))
                .build();


    }
}
