package com.doroshenko.serhey.person.service.person;

import com.doroshenko.serhey.person.domain.person.Person;
import com.doroshenko.serhey.person.dto.person.PersonDto;

/**
 * Represents service layer of {@link Person} entity
 *
 * @author Serhey Doroshenko
 */
public interface PersonService {

    PersonDto loadOne(long id);

    PersonDto saveOne(PersonDto dto);

}
