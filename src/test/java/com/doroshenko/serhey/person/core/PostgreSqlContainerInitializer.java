package com.doroshenko.serhey.person.core;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class PostgreSqlContainerInitializer {

    private static final String postgreSqlImage = "postgres:latest";
    private static final PostgreSQLContainer<?> postgreSqlContainer = new PostgreSQLContainer<>(postgreSqlImage)
            .withUsername("test_user")
            .withPassword("test_password")
            .withDatabaseName("person_test_db")
            .withReuse(true);

    static {
        postgreSqlContainer.start();
    }

    @DynamicPropertySource
    public static void datasourceConfig(final DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSqlContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSqlContainer::getPassword);
        registry.add("spring.datasource.username", postgreSqlContainer::getUsername);
    }

}
