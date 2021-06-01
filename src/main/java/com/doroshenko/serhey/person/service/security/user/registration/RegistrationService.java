package com.doroshenko.serhey.person.service.security.user.registration;

import com.doroshenko.serhey.person.dto.security.user.InternalUserDto;
import com.doroshenko.serhey.person.dto.security.user.registration.InternalUserRegistrationDto;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Service for user's registration in system
 *
 * @author Serhey Doroshenko
 */
public interface RegistrationService {

    InternalUserDto signUp(@RequestBody final InternalUserRegistrationDto dto);

}
