package com.doroshenko.serhey.person.service.person;

import com.doroshenko.serhey.person.domain.person.Person;
import com.doroshenko.serhey.person.dto.person.PersonDto;
import com.doroshenko.serhey.person.filter.person.PersonQueryFilter;
import com.doroshenko.serhey.person.service.core.api.FilterableService;
import org.springframework.data.domain.Page;

/**
 * Represents service layer of {@link Person} entity
 *
 * @author Serhey Doroshenko
 */
public interface PersonService extends FilterableService<Person> {

    PersonDto loadOne(long id);

    PersonDto saveOne(PersonDto dto);

    Page<PersonDto> loadAllByFilter(PersonQueryFilter filter);

}
