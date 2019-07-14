package kov.irok.WebClient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("kov.irok"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Chat REST API",
                "Chat for individual communication agent with the client",
                "1.0",
                "Terms of service",
                new Contact("Iryna Kavaliova", "https://someUrl", "iirka86@mail.ru"),
                "License Version 2.0",
                "https://someUrl",
                getVendorEx()
        );
        return apiInfo;
    }

    private Collection<VendorExtension> getVendorEx() {
        List<VendorExtension> list = new ArrayList<>();
        list.add(new StringVendorExtension(" ", " "));
        return list;
    }
}
