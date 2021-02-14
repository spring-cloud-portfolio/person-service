package com.doroshenko.serhey.person.repository.person;

import com.doroshenko.serhey.person.domain.person.Person;
import com.doroshenko.serhey.person.repository.core.FilterableRepository;

/**
 * Represents the repository layer of the {@link Person} entity
 *
 * @author Serhey Doroshenko
 * @see FilterableRepository
 */
public interface PersonRepository extends FilterableRepository<Person> {
}
