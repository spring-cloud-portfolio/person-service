package com.doroshenko.serhey.person.repository.person.spec;

import com.doroshenko.serhey.person.domain.person.Person;
import com.doroshenko.serhey.person.domain.person.Person_;
import com.doroshenko.serhey.person.filter.person.PersonQueryFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Repository
public class PersonSpec {

    public Specification<Person> extract(final PersonQueryFilter filter) {
        return (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            // Gender
            checkNullElseDo(filter.getGender(), g -> predicates.add(cb.equal(root.get(Person_.gender), g)));
            // Birthday
            checkNullElseDo(filter.getBirthDay(), birthDay -> predicates.add(cb.equal(root.get(Person_.birthDay), birthDay)));
            // Lastname
            checkEmptyElseDo(filter.getLastName(), s -> predicates.add(cb.like(cb.lower(root.get(Person_.lastName)), s.toLowerCase())));
            // Firstname
            checkEmptyElseDo(filter.getFirstName(), s -> predicates.add(cb.like(cb.lower(root.get(Person_.firstName)), s.toLowerCase())));
            // Middle name
            checkEmptyElseDo(filter.getMiddleName(), s -> predicates.add(cb.like(cb.lower(root.get(Person_.middleName)), s.toLowerCase())));
            // Person types
            if (!filter.getPersonTypes().isEmpty()) {
                // FIXME: 08.03.2021 add peron type predicate
            }

            return predicates.isEmpty() ? null : cb.and(predicates.toArray(Predicate[]::new));
        };
    }

    private <T> void checkNullElseDo(final T actor, final Consumer<T> consumer) {
        if (actor != null) consumer.accept(actor);
    }

    private void checkEmptyElseDo(final String actor, final Consumer<String> consumer) {
        if (StringUtils.hasText(actor)) consumer.accept(actor);
    }

}
