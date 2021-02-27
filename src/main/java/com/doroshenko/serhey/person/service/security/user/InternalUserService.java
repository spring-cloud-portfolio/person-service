package com.doroshenko.serhey.person.service.security.user;

import com.doroshenko.serhey.person.domain.security.InternalUser;
import com.doroshenko.serhey.person.dto.security.user.InternalUserDto;
import com.doroshenko.serhey.person.filter.security.user.InternalUserQueryFilter;
import org.springframework.data.domain.Page;

/**
 * Represents service layer of {@link InternalUser} entity
 *
 * @author Serhey Doroshenko
 */
public interface InternalUserService {
    Page<InternalUserDto> loadAllByFilter(InternalUserQueryFilter filter);
}
