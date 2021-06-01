package com.doroshenko.serhey.person.domain.core.api;

import com.doroshenko.serhey.person.domain.person.Person;

/**
 * Determines entities with personal data
 *
 * @author Serhey Doroshenko
 */
public interface PersonAware {

    Person getPerson();

    void setPerson(Person person);

}
