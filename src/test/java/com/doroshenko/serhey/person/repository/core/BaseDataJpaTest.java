package com.doroshenko.serhey.person.repository.core;

import com.doroshenko.serhey.person.core.PostgreSqlContainerInitializer;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public abstract class BaseDataJpaTest extends PostgreSqlContainerInitializer {
}
