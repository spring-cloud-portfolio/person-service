package com.doroshenko.serhey.person.service.security.user.registration;

import com.doroshenko.serhey.person.domain.person.Person;
import com.doroshenko.serhey.person.domain.security.InternalUser;
import com.doroshenko.serhey.person.dto.security.user.InternalUserDto;
import com.doroshenko.serhey.person.dto.security.user.registration.InternalUserRegistrationDto;
import com.doroshenko.serhey.person.enumeration.person.PersonType;
import com.doroshenko.serhey.person.repository.security.user.InternalUserRepository;
import com.doroshenko.serhey.person.service.security.user.mapper.InternalUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link RegistrationService}
 *
 * @author Serhey Doroshenko
 * @see RegistrationService
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final InternalUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final InternalUserRepository userRepository;

    @Override
    public InternalUserDto signUp(final InternalUserRegistrationDto dto) {
        final InternalUser user = new InternalUser();
        user.setEnabled(Boolean.TRUE);
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        // Person
        final Person person = new Person();
        person.setPersonTypes(PersonType.INTERNAL_USER);
        user.setPerson(person);
        return userMapper.toDto(userRepository.save(user));
    }

    @Autowired
    public RegistrationServiceImpl(final InternalUserMapper userMapper,
                                   final PasswordEncoder passwordEncoder,
                                   final InternalUserRepository userRepository) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

}
