package com.doroshenko.serhey.person.repository.core.api;

import com.doroshenko.serhey.person.dto.core.api.Identifiable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Extends the {@link CommonRepository} by adding the ability
 * to filter queries using the {@link org.springframework.data.jpa.domain.Specification}
 *
 * @param <E> entity
 * @author Serhey Doroshenko
 * @see CommonRepository
 * @see JpaSpecificationExecutor
 */
@NoRepositoryBean
public interface FilterableRepository<E extends Identifiable> extends CommonRepository<E>, JpaSpecificationExecutor<E> {
}
