package com.doroshenko.serhey.person.web.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Open API v3 REST documentation configuration
 *
 * @author Serhey Doroshenko
 */
@Configuration
@Profile("!production")
public class OpenApiConfig {

    @Bean
    public OpenAPI fbuBoxAPI(final BuildProperties buildProperties) {
        return new OpenAPI().info(new Info().title("Person Service API").version(buildProperties.getVersion()));
    }

    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("All")
                .pathsToMatch("/**")
                .build();
    }

}
