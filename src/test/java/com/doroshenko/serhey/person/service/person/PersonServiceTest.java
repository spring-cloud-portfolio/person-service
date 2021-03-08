package com.doroshenko.serhey.person.service.person;

import com.doroshenko.serhey.person.core.BaseSpringBootTest;
import com.doroshenko.serhey.person.dto.person.PersonDto;
import com.doroshenko.serhey.person.filter.person.PersonQueryFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Sql(scripts = {"classpath:fixture/person/person.sql"})
class PersonServiceTest extends BaseSpringBootTest {

    @Autowired
    private PersonService personService;

    @Test
    void loadOneTest() {
        final PersonDto personDto = personService.loadOne(100);
        Assertions.assertNotNull(personDto);
        Assertions.assertFalse(personDto.getPersonTypes().isEmpty());
    }

    @Test
    void loadAllByFilterTest() {
        final PersonQueryFilter filter = new PersonQueryFilter(0, 10, null, "", "", null, "", false, "", null);
        final Page<PersonDto> page = personService.loadAllByFilter(filter);
        Assertions.assertTrue(page.hasContent());
    }

}
