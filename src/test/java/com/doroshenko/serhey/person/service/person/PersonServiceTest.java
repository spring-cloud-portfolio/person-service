package com.doroshenko.serhey.person.service.person;

import com.doroshenko.serhey.person.core.BaseSpringBootTest;
import com.doroshenko.serhey.person.dto.person.PersonDto;
import com.doroshenko.serhey.person.enumeration.person.PersonType;
import com.doroshenko.serhey.person.filter.person.PersonQueryFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;

import java.util.EnumSet;
import java.util.Set;

@Sql(scripts = {"classpath:fixture/person/person_insert.sql"})
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {"classpath:fixture/person/person_truncate.sql"})
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
        final Set<PersonType> personTypes = EnumSet.of(PersonType.INTERNAL_USER);
        final PersonQueryFilter filter = new PersonQueryFilter(0, 10, null, "L", "", null, "", false, "", personTypes);
        final Page<PersonDto> page = personService.loadAllByFilter(filter);
        Assertions.assertTrue(page.hasContent());
        Assertions.assertEquals(2, page.getTotalElements());
    }

}
