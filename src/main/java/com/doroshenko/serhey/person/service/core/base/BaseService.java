package com.doroshenko.serhey.person.service.core.base;

import com.doroshenko.serhey.person.dto.core.api.Identifiable;
import com.doroshenko.serhey.person.repository.core.api.FilterableRepository;
import com.doroshenko.serhey.person.service.core.api.CommonService;

public abstract class BaseService<E extends Identifiable> implements CommonService<E> {

}
