package com.doroshenko.serhey.person.service.person.mapper;

import com.doroshenko.serhey.person.domain.person.Person;
import com.doroshenko.serhey.person.dto.person.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDto toDto(Person source);

    Person toDomain(PersonDto source);

    Person toDomain(PersonDto source, @MappingTarget Person target);

}
