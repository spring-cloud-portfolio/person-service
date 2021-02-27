package com.doroshenko.serhey.person.service.security.user;

import com.doroshenko.serhey.person.dto.security.user.InternalUserDto;
import com.doroshenko.serhey.person.filter.security.user.InternalUserQueryFilter;
import com.doroshenko.serhey.person.repository.security.user.InternalUserRepository;
import com.doroshenko.serhey.person.repository.security.user.spec.InternalUserSpecExtractor;
import com.doroshenko.serhey.person.service.security.user.mapper.InternalUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link InternalUserService}
 *
 * @author Serhey Doroshenko
 * @see InternalUserService
 */
@Service
public class InternalUserServiceImpl implements InternalUserService {

    private final InternalUserMapper userMapper;
    private final InternalUserRepository userRepository;
    private final InternalUserSpecExtractor userSpecExtractor;

    @Override
    public Page<InternalUserDto> loadAllByFilter(final InternalUserQueryFilter filter) {
        final PageRequest pageRequest = PageRequest.of(filter.getPage(), filter.getSize());
        return userRepository.findAll(userSpecExtractor.extract(filter), pageRequest).map(userMapper::toDto);
    }

    @Autowired
    public InternalUserServiceImpl(final InternalUserMapper userMapper,
                                   final InternalUserRepository userRepository,
                                   final InternalUserSpecExtractor userSpecExtractor) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.userSpecExtractor = userSpecExtractor;
    }

}
