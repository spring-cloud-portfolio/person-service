package com.doroshenko.serhey.person.service.core.base;

import com.doroshenko.serhey.person.dto.core.api.Identifiable;
import com.doroshenko.serhey.person.dto.core.base.BaseQueryFilter;
import com.doroshenko.serhey.person.service.core.api.FilterableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.data.domain.Sort.by;

/**
 * @param <E> entity
 */
public abstract class BaseFilterableService<E extends Identifiable> extends BaseService<E> implements FilterableService<E> {

    protected <F extends BaseQueryFilter> Pageable extractPageable(final F filter) {
        final String sortingField = filter.getSortingField();
        if (!StringUtils.hasText(sortingField)) return of(filter.getPage(), filter.getSize());
        return of(filter.getPage(), filter.getSize(), by(filter.isDescending() ? DESC : ASC, sortingField));
    }

    public <F extends BaseQueryFilter, S extends Specification<E>> Page<E> loadAllByFilter(final F filter, final S spec) {
        return repository().findAll(spec, extractPageable(filter));
    }

}
