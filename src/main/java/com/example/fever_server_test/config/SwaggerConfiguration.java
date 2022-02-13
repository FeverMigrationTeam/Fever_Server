package com.example.fever_server_test.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket apis(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Fever-APIs")
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.fever_server_test"))
//                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    @Bean
    public Docket apisControllerTest(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("basePackage 분류 테스트-stadium")
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.fever_server_test.controller.stadium"))
//                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo2());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Fever")
                .description("Spring boot Server")
                .version("2.0")
                .build();
    }
    private ApiInfo apiInfo2() {
        return new ApiInfoBuilder()
                .title("테스트")
                .description("분류 테스트")
                .version("2.0")
                .build();
    }
}
