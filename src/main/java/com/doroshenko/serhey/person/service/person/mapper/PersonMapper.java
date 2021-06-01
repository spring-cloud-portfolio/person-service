package com.doroshenko.serhey.person.service.person.mapper;

import com.doroshenko.serhey.person.domain.person.Person;
import com.doroshenko.serhey.person.dto.person.PersonDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDto toDto(Person source);

    @Mapping(target = "personTypeSet", ignore = true)
    Person toDomain(PersonDto source);

    @InheritConfiguration
    Person toDomain(PersonDto source, @MappingTarget Person target);

}
