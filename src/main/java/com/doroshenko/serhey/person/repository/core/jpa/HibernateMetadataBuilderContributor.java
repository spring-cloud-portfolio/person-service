package com.doroshenko.serhey.person.repository.core.jpa;

import com.doroshenko.serhey.person.repository.core.config.RepositoryConfig;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.BooleanType;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;

/**
 * Designed for custom SQL functions and types registration.
 * Declared through {@link HibernatePropertiesCustomizer} in {@link RepositoryConfig}.
 *
 * @author Serhey Doroshenko
 * @see MetadataBuilderContributor
 */
public class HibernateMetadataBuilderContributor implements MetadataBuilderContributor {

    public static final String I_LIKE_FN = "i_like";
    public static final String PERSON_TYPES_CONTAINS_FN = "person_types_contains";

    @Override
    public void contribute(final MetadataBuilder metadataBuilder) {
        metadataBuilder.applySqlFunction(I_LIKE_FN, new SQLFunctionTemplate(BooleanType.INSTANCE, "(?1 ilike ?2)"));
        metadataBuilder.applySqlFunction(PERSON_TYPES_CONTAINS_FN, new SQLFunctionTemplate(BooleanType.INSTANCE, "(?1 && ?2::person_type_enum[])", true));
    }

}
