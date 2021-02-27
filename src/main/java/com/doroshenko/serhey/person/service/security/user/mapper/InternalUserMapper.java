package com.doroshenko.serhey.person.service.security.user.mapper;

import com.doroshenko.serhey.person.domain.security.InternalUser;
import com.doroshenko.serhey.person.dto.security.user.InternalUserDto;
import com.doroshenko.serhey.person.service.person.mapper.PersonMapper;
import org.mapstruct.Mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", uses = {PersonMapper.class}, injectionStrategy = CONSTRUCTOR)
public interface InternalUserMapper {

    InternalUserDto toDto(InternalUser source);

}
