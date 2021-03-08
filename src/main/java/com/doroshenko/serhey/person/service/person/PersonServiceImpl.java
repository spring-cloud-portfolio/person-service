package com.doroshenko.serhey.person.service.person;

import com.doroshenko.serhey.person.domain.person.Person;
import com.doroshenko.serhey.person.dto.person.PersonDto;
import com.doroshenko.serhey.person.filter.person.PersonQueryFilter;
import com.doroshenko.serhey.person.repository.person.PersonRepository;
import com.doroshenko.serhey.person.repository.person.spec.PersonSpec;
import com.doroshenko.serhey.person.service.core.base.BaseFilterableService;
import com.doroshenko.serhey.person.service.person.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

/**
 * Implementation of {@link PersonService}
 *
 * @author Serhey Doroshenko
 * @see PersonService
 */
@Service
public class PersonServiceImpl extends BaseFilterableService<Person> implements PersonService {

    public static final String NOT_FOUND_EX = "Person not found";

    private final PersonSpec personSpec;
    private final PersonMapper personMapper;
    private final PersonRepository personRepository;

    @Override
    @Transactional(readOnly = true)
    public PersonDto loadOne(final long id) {
        return repository().findById(id)
                .map(personMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(NOT_FOUND_EX));
    }

    @Override
    @Transactional
    public PersonDto saveOne(final PersonDto dto) {
        final Person person = dto.isNew() ? personMapper.toDomain(dto)
                : personMapper.toDomain(dto, repository().getOne(dto.getId()));
        return personMapper.toDto(repository().save(person));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PersonDto> loadAllByFilter(final PersonQueryFilter filter) {
        return loadAllByFilter(filter, personSpec.extract(filter)).map(personMapper::toDto);
    }

    @Override
    public PersonRepository repository() {
        return personRepository;
    }

    @Autowired
    public PersonServiceImpl(final PersonSpec personSpec,
                             final PersonMapper personMapper,
                             final PersonRepository personRepository) {
        this.personSpec = personSpec;
        this.personMapper = personMapper;
        this.personRepository = personRepository;
    }

}
