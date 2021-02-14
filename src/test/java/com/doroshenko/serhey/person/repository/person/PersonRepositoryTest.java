package com.doroshenko.serhey.person.repository.person;

import com.doroshenko.serhey.person.repository.core.BaseDataJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PersonRepositoryTest extends BaseDataJpaTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    void findOneTest() {
        assertDoesNotThrow(() -> personRepository.findById(1L));
    }

}
