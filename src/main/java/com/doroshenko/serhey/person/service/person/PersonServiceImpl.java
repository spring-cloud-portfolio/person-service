package com.doroshenko.serhey.person.service.person;

import com.doroshenko.serhey.person.dto.person.PersonDto;
import com.doroshenko.serhey.person.repository.person.PersonRepository;
import com.doroshenko.serhey.person.service.person.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PersonServiceImpl implements PersonService {

    public static final String NOT_FOUND_EX = "Person not found";

    private final PersonMapper personMapper;
    private final PersonRepository personRepository;

    @Override
    public PersonDto loadOne(final long id) {
        return personRepository.findById(id)
                .map(personMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_EX));
    }

    @Autowired
    public PersonServiceImpl(final PersonMapper personMapper,
                             final PersonRepository personRepository) {
        this.personMapper = personMapper;
        this.personRepository = personRepository;
    }

}
