package com.doroshenko.serhey.person.repository.security.user;

import com.doroshenko.serhey.person.domain.security.InternalUser;
import com.doroshenko.serhey.person.repository.core.api.FilterableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

/**
 * Represents the repository layer of the {@link InternalUser} entity
 *
 * @author Serhey Doroshenko
 * @see FilterableRepository
 */
@Repository
public interface InternalUserRepository extends FilterableRepository<InternalUser> {

    @NonNull
    @Override
    @EntityGraph(attributePaths = {"person"})
    Page<InternalUser> findAll(Specification<InternalUser> spec, @NonNull Pageable pageable);

}
