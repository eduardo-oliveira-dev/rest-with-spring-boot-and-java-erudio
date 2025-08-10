package com.eduardooliveira.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        //Via Query Parameter
        /*configurer
                .favorParameter(true) // Usa parâmetro ?mediaType=json
                .parameterName("mediaType")
                .ignoreAcceptHeader(false) // Respeita o cabeçalho Accept
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML); */

        // Via Header
        /* configurer
                .favorParameter(false) // Usa parâmetro ?mediaType=json
                .parameterName("mediaType")
                .ignoreAcceptHeader(false) // Respeita o cabeçalho Accept
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML); */

        // Add support to yaml
        configurer
                .favorParameter(false)
                .parameterName("mediaType")
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("yaml", MediaType.APPLICATION_YAML);

    }

}
