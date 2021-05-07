package com.doroshenko.serhey.person.service.security.user;

import com.doroshenko.serhey.person.core.BaseSpringBootTest;
import com.doroshenko.serhey.person.dto.security.user.InternalUserDto;
import com.doroshenko.serhey.person.filter.security.user.InternalUserQueryFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;

@Sql({
        "classpath:fixture/person/person_insert.sql",
        "classpath:fixture/security/internal_user_insert.sql",
        "classpath:fixture/security/internal_user_role_insert.sql",
        "classpath:fixture/security/internal_user_to_role_insert.sql",
        "classpath:fixture/security/internal_user_role_authority_insert.sql",
        "classpath:fixture/security/internal_user_role_to_authority_insert.sql"
})
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {
        "classpath:fixture/person/person_truncate.sql",
        "classpath:fixture/security/internal_user_truncate.sql",
        "classpath:fixture/security/internal_user_role_truncate.sql",
        "classpath:fixture/security/internal_user_to_role_truncate.sql",
        "classpath:fixture/security/internal_user_role_authority_truncate.sql",
        "classpath:fixture/security/internal_user_role_to_authority_truncate.sql"
})
class InternalUserServiceTest extends BaseSpringBootTest {

    @Autowired
    private InternalUserService userService;

    @Test
    void loadAllByFilterTest() {
        final InternalUserQueryFilter filter = new InternalUserQueryFilter(0, 2, "user", false, "");
        final Page<InternalUserDto> page = userService.loadAllByFilter(filter);
        Assertions.assertTrue(page.hasContent());
    }

}
