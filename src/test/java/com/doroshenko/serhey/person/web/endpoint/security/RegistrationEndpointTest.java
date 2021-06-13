package com.doroshenko.serhey.person.web.endpoint.security;

import com.doroshenko.serhey.person.core.BaseSpringBootTest;
import com.doroshenko.serhey.person.dto.person.PersonDto;
import com.doroshenko.serhey.person.dto.security.user.registration.InternalUserRegistrationDto;
import com.doroshenko.serhey.person.enumeration.person.Gender;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@AutoConfigureMockMvc
class RegistrationEndpointTest extends BaseSpringBootTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void signUpTest() throws Exception {
        final PersonDto person = new PersonDto();
        person.setGender(Gender.MALE);
        person.setLastName("LastName1");
        person.setFirstName("FirstName1");
        person.setMiddleName("MiddleName1");
        person.setBirthDay(LocalDate.of(1987, 5, 25));
        final InternalUserRegistrationDto dto = new InternalUserRegistrationDto(
                "demo1@email.com",
                "UserName1",
                "password1",
                person,
                "+380501236587",
                "password1"
        );
        final MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/registration/sign-up.json")
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(post)
                .andDo(MockMvcResultHandlers.log())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
