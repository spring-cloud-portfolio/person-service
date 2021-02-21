package com.doroshenko.serhey.person.repository.person;

import com.doroshenko.serhey.person.domain.person.Person;
import com.doroshenko.serhey.person.enumeration.person.Gender;
import com.doroshenko.serhey.person.repository.core.BaseDataJpaTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

class PersonRepositoryTest extends BaseDataJpaTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    void findOneNullTest() {
        Assertions.assertNull(personRepository.findById(100L).orElse(null));
    }

    @Test
    @Sql({"classpath:fixture/person/person.sql"})
    void findOneNotNullTest() {
        Assertions.assertNotNull(personRepository.findById(100L).orElse(null));
    }

    @Test
    void saveTest() {
        final Person person = new Person();
        person.setGender(Gender.MALE);
        person.setLastName("LastName1");
        person.setFirstName("FirstName1");
        person.setMiddleName("MiddleName1");
        person.setBirthDay(LocalDate.now());
        final Person savedPerson = personRepository.save(person);
        Assertions.assertNotNull(savedPerson);
        Assertions.assertNotNull(savedPerson.getId());
    }

}
