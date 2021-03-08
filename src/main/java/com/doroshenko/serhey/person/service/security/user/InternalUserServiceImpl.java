package com.doroshenko.serhey.person.service.security.user;

import com.doroshenko.serhey.person.domain.security.InternalUser;
import com.doroshenko.serhey.person.dto.security.user.InternalUserDto;
import com.doroshenko.serhey.person.filter.security.user.InternalUserQueryFilter;
import com.doroshenko.serhey.person.repository.security.user.InternalUserRepository;
import com.doroshenko.serhey.person.repository.security.user.spec.InternalUserSpec;
import com.doroshenko.serhey.person.service.core.base.BaseFilterableService;
import com.doroshenko.serhey.person.service.security.user.mapper.InternalUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link InternalUserService}
 *
 * @author Serhey Doroshenko
 * @see InternalUserService
 */
@Service
public class InternalUserServiceImpl extends BaseFilterableService<InternalUser> implements InternalUserService {

    private final InternalUserSpec userSpec;
    private final InternalUserMapper userMapper;
    private final InternalUserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<InternalUserDto> loadAllByFilter(final InternalUserQueryFilter filter) {
        return loadAllByFilter(filter, userSpec.extract(filter)).map(userMapper::toDto);
    }

    @Override
    public InternalUserRepository repository() {
        return userRepository;
    }

    @Autowired
    public InternalUserServiceImpl(final InternalUserSpec userSpec,
                                   final InternalUserMapper userMapper,
                                   final InternalUserRepository userRepository) {
        this.userSpec = userSpec;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

}
