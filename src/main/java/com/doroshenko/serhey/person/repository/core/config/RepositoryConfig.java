package com.doroshenko.serhey.person.repository.core.config;

import com.doroshenko.serhey.person.repository.core.jpa.HibernateMetadataBuilderContributor;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration(proxyBeanMethods = false)
public class RepositoryConfig implements HibernatePropertiesCustomizer {

    @Override
    public void customize(final Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.metadata_builder_contributor", new HibernateMetadataBuilderContributor());
    }
}
