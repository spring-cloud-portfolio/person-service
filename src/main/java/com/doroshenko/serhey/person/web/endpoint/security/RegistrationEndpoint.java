package com.doroshenko.serhey.person.web.endpoint.security;

import com.doroshenko.serhey.person.dto.security.user.InternalUserDto;
import com.doroshenko.serhey.person.dto.security.user.registration.InternalUserRegistrationDto;
import com.doroshenko.serhey.person.service.security.user.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Handles HTTP requests connected with user registration
 *
 * @author Serhey Doroshenko
 */
@RestController
@RequestMapping("/registration")
public class RegistrationEndpoint {

    private final RegistrationService registrationService;

    @PostMapping("/sign-up.json")
    @ResponseStatus(HttpStatus.CREATED)
    public InternalUserDto signUp(@Validated @RequestBody final InternalUserRegistrationDto dto) {
        return registrationService.signUp(dto);
    }

    @Autowired
    public RegistrationEndpoint(final RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

}
