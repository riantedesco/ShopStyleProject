package com.compass.mscustomer.configuration.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.compass.mscustomer.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessageGet())
                .globalResponseMessage(RequestMethod.POST, responseMessagePost())
                .globalResponseMessage(RequestMethod.PUT, responseMessagePut())
                .globalResponseMessage(RequestMethod.DELETE, responseMessageDelete())
                .apiInfo(apiInfo());
    }

    private List<ResponseMessage> responseMessagePost() {
        return new ArrayList<ResponseMessage>() {
            {
                add(new ResponseMessageBuilder()
                        .code(200)
                        .message("Ok")
                        .build());
                add(new ResponseMessageBuilder()
                        .code(404)
                        .message("Not found")
                        .build());
            }
        };
    }

    private List<ResponseMessage> responseMessageGet() {
        return new ArrayList<ResponseMessage>() {
            {
                add(new ResponseMessageBuilder()
                        .code(201)
                        .message("Created")
                        .build());
                add(new ResponseMessageBuilder()
                        .code(404)
                        .message("Not found")
                        .build());
            }
        };
    }

    private List<ResponseMessage> responseMessagePut() {
        return new ArrayList<ResponseMessage>() {
            {
                add(new ResponseMessageBuilder()
                        .code(200)
                        .message("Ok")
                        .build());
                add(new ResponseMessageBuilder()
                        .code(400)
                        .message("Bad request")
                        .build());
                add(new ResponseMessageBuilder()
                        .code(404)
                        .message("Not found")
                        .build());
            }
        };
    }

    private List<ResponseMessage> responseMessageDelete() {
        return new ArrayList<ResponseMessage>() {
            {
                add(new ResponseMessageBuilder()
                        .code(204)
                        .message("No content")
                        .build());
                add(new ResponseMessageBuilder()
                        .code(404)
                        .message("Not found")
                        .build());
            }
        };
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Mscustomer")
                .description("Documentação do mscustomer do projeto ShopStyle")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("Rian Tedesco", null, "rian.tedesco@compasso.com.br"))
                .build();
    }
}
