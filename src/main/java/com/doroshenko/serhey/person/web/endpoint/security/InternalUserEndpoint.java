package com.doroshenko.serhey.person.web.endpoint.security;

import com.doroshenko.serhey.person.dto.security.user.InternalUserDto;
import com.doroshenko.serhey.person.filter.security.user.InternalUserQueryFilter;
import com.doroshenko.serhey.person.service.security.user.InternalUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Handles HTTP requests connected with user
 *
 * @author Serhey Doroshenko
 */
@RestController
@RequestMapping(path = "/user", produces = APPLICATION_JSON_VALUE)
public class InternalUserEndpoint {

    private final InternalUserService userService;

    @GetMapping(path = "/get-all.json")
    public Page<InternalUserDto> getAllByFilter(@ModelAttribute final InternalUserQueryFilter filter) {
        return userService.loadAllByFilter(filter);
    }

    @Autowired
    public InternalUserEndpoint(final InternalUserService userService) {
        this.userService = userService;
    }

}
