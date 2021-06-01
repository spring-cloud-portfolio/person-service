package com.doroshenko.serhey.person.web.endpoint.security;

import com.doroshenko.serhey.person.dto.security.user.InternalUserDto;
import com.doroshenko.serhey.person.dto.security.user.registration.InternalUserRegistrationDto;
import com.doroshenko.serhey.person.service.security.user.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public InternalUserDto signUp(@RequestBody final InternalUserRegistrationDto dto) {
        return registrationService.signUp(dto);
    }

    @Autowired
    public RegistrationEndpoint(final RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

}
