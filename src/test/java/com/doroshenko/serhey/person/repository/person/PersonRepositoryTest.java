package com.doroshenko.serhey.person.repository.person;

import com.doroshenko.serhey.person.domain.person.Person;
import com.doroshenko.serhey.person.repository.core.BaseDataJpaTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PersonRepositoryTest extends BaseDataJpaTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    void findOneTest() {
        assertDoesNotThrow(() -> personRepository.findById(1L));
    }

    @Test
    void saveTest() {
        final Person person = new Person();
        person.setLastName("LastName1");
        person.setFirstName("FirstName1");
        person.setMiddleName("MiddleName1");
        person.setBirthDay(LocalDate.now());

        final Person savedPerson = personRepository.save(person);
        Assertions.assertNotNull(savedPerson);
        Assertions.assertNotNull(savedPerson.getId());
    }

}
