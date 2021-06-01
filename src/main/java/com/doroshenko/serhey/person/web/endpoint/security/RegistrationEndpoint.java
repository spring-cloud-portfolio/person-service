package com.doroshenko.serhey.person.web.endpoint.security;

import com.doroshenko.serhey.person.dto.security.user.InternalUserDto;
import com.doroshenko.serhey.person.dto.security.user.registration.InternalUserRegistrationDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationEndpoint {

    @PostMapping("/sign-up.json")
    public InternalUserDto signUp(@RequestBody final InternalUserRegistrationDto dto) {
        return null;
    }

}
