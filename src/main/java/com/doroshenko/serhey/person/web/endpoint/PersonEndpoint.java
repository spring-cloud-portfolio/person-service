package com.doroshenko.serhey.person.web.endpoint;

import com.doroshenko.serhey.person.dto.person.PersonDto;
import com.doroshenko.serhey.person.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Handles HTTP requests connected with person
 *
 * @author Serhey Doroshenko
 */
@RestController
@RequestMapping(path = "/person", produces = APPLICATION_JSON_VALUE)
public class PersonEndpoint {

    private final PersonService personService;

    @GetMapping(path = "/{id}/get-one.json")
    public PersonDto getOne(@PathVariable final long id) {
        return personService.loadOne(id);
    }

    @PostMapping(path = "/save-one.json", consumes = APPLICATION_JSON_VALUE)
    public PersonDto saveOne(@RequestBody final PersonDto dto) {
        return personService.saveOne(dto);
    }

    @Autowired
    public PersonEndpoint(final PersonService personService) {
        this.personService = personService;
    }

}
