package com.doroshenko.serhey.person.repository.security;

import com.doroshenko.serhey.person.core.annotation.NoOpSql;
import com.doroshenko.serhey.person.domain.security.InternalUser;
import com.doroshenko.serhey.person.repository.core.BaseDataJpaTest;
import com.doroshenko.serhey.person.repository.security.user.InternalUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@Sql({
        "classpath:fixture/person/person_insert.sql",
        "classpath:fixture/security/internal_user_insert.sql",
        "classpath:fixture/security/internal_user_role_insert.sql",
        "classpath:fixture/security/internal_user_to_role_insert.sql",
        "classpath:fixture/security/internal_user_role_authority_insert.sql",
        "classpath:fixture/security/internal_user_role_to_authority_insert.sql"
})
class InternalUserRepositoryTest extends BaseDataJpaTest {

    @Autowired
    private InternalUserRepository userRepository;

    @Test
    @NoOpSql
    void findOneNullTest() {
        Assertions.assertNull(userRepository.findById(100L).orElse(null));
    }

    @Test
    void findOneNotNullTest() {
        final InternalUser user = userRepository.findById(100L).orElse(null);
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getPerson());
        Assertions.assertFalse(user.getRoles().isEmpty());
    }

}
