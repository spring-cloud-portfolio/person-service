package com.doroshenko.serhey.person.service.core.api;

import com.doroshenko.serhey.person.dto.core.api.Identifiable;
import com.doroshenko.serhey.person.repository.core.api.FilterableRepository;

public interface CommonService<E extends Identifiable> {

    FilterableRepository<E> repository();

}
