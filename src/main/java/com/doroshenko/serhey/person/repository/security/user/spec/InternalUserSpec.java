package com.doroshenko.serhey.person.repository.security.user.spec;

import com.doroshenko.serhey.person.domain.person.Person;
import com.doroshenko.serhey.person.domain.person.Person_;
import com.doroshenko.serhey.person.domain.security.InternalUser;
import com.doroshenko.serhey.person.domain.security.InternalUser_;
import com.doroshenko.serhey.person.filter.security.user.InternalUserQueryFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InternalUserSpec {

    public Specification<InternalUser> extract(final InternalUserQueryFilter filter) {
        return (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(filter.getUsername())) {
                predicates.add(cb.like(cb.lower(root.get(InternalUser_.username)), filter.getUsername().toLowerCase().concat("%")));
            }

            return predicates.isEmpty() ? null : cb.and(predicates.toArray(Predicate[]::new));
        };
    }

}
