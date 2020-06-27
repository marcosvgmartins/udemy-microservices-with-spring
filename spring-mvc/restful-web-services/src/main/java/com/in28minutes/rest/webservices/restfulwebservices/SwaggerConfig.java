package com.in28minutes.rest.webservices.restfulwebservices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * The API docs are available at http://localhost:8080/v2/api-docs
 *
 * The API browser is available at http://localhost:8080/swagger-ui.html
 */

// Configuration
@Configuration
// Enable Swagger
//EnableSwagger2WebMvc is used in Swagger 3.0.0-SNAPSHOT
@EnableSwagger2WebMvc
public class SwaggerConfig {

    private static final Contact DEFAULT_CONTACT = new Contact("Marcos Martins", "www.github.com/marcosvgmartins",
            "marcos@mail.com");

    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Spring Boot and MVC API Title",
            "API developed during the Microservices with Spring course", "1.0", "", DEFAULT_CONTACT, "Apache 2.0",
            "www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
        new HashSet<String>(Arrays.asList("application/json", "application/xml"));

    /*@Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<LinkDiscoverer>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }*/

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(DEFAULT_API_INFO)
            .produces(DEFAULT_PRODUCES_AND_CONSUMES)
            .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
    }
}