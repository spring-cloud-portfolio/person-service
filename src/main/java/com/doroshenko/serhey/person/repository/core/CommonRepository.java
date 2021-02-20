package com.doroshenko.serhey.person.repository.core;

import com.doroshenko.serhey.person.dto.core.api.Identifiable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


/**
 * Common repository for entities with a {@link Long} type identifier
 *
 * @param <E> entity
 * @author Serhey Doroshenko
 */
@NoRepositoryBean
public interface CommonRepository<E extends Identifiable> extends JpaRepository<E, Long> {
}
