package com.doroshenko.serhey.person.repository.security.user;

import com.doroshenko.serhey.person.domain.security.InternalUser;
import com.doroshenko.serhey.person.repository.core.FilterableRepository;
import org.springframework.stereotype.Repository;

/**
 * Represents the repository layer of the {@link InternalUser} entity
 *
 * @author Serhey Doroshenko
 * @see FilterableRepository
 */
@Repository
public interface InternalUserRepository extends FilterableRepository<InternalUser> {
}
