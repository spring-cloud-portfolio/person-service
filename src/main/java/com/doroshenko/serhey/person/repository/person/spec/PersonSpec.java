package com.doroshenko.serhey.person.repository.person.spec;

import com.doroshenko.serhey.person.domain.person.Person;
import com.doroshenko.serhey.person.domain.person.Person_;
import com.doroshenko.serhey.person.filter.person.PersonQueryFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static com.doroshenko.serhey.person.core.util.ConditionUtils.*;
import static com.doroshenko.serhey.person.repository.core.util.CriteriaUtils.iStartingWith;
import static com.doroshenko.serhey.person.repository.core.util.CriteriaUtils.personTypesContainsFn;

@Repository
public class PersonSpec {

    public Specification<Person> extract(final PersonQueryFilter filter) {
        return (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            // Gender
            doIfNonNull(filter.getGender(), g -> predicates.add(cb.equal(root.get(Person_.gender), g)));
            // Birthday
            doIfNonNull(filter.getBirthDay(), birthDay -> predicates.add(cb.equal(root.get(Person_.birthDay), birthDay)));
            // Lastname
//            doIfHasText(filter.getLastName(), s -> predicates.add(iLikeFn(root.get(Person_.lastName), s + "%").apply(cb)));
            doIfHasText(filter.getLastName(), s -> predicates.add(iStartingWith(root.get(Person_.lastName), s).apply(cb)));
            // Firstname
            doIfHasText(filter.getFirstName(), s -> predicates.add(iStartingWith(root.get(Person_.firstName), s).apply(cb)));
            // Middle name
            doIfHasText(filter.getMiddleName(), s -> predicates.add(iStartingWith(root.get(Person_.middleName), s).apply(cb)));
            // Person types
            doIfNonEmpty(filter.getPersonTypes(), types -> predicates.add(personTypesContainsFn(root.get(Person_.personTypes), types).apply(cb)));
            return predicates.isEmpty() ? null : cb.and(predicates.toArray(Predicate[]::new));
        };
    }

}
