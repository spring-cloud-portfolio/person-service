package com.doroshenko.serhey.person.repository.person.spec;

import com.doroshenko.serhey.person.domain.person.Person;
import com.doroshenko.serhey.person.domain.person.Person_;
import com.doroshenko.serhey.person.enumeration.person.PersonType;
import com.doroshenko.serhey.person.filter.person.PersonQueryFilter;
import com.vladmihalcea.hibernate.type.array.EnumArrayType;
import com.vladmihalcea.hibernate.type.array.internal.AbstractArrayType;
import com.vladmihalcea.hibernate.type.array.internal.AbstractArrayTypeDescriptor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;

import static com.vladmihalcea.hibernate.type.array.internal.AbstractArrayType.SQL_ARRAY_TYPE;

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
//                predicates.add(root.get(Person_.personTypes).in(cb.literal(filter.getPersonTypes().toArray(PersonType[]::new))));
//                final Expression<Void> cast = cb.function("try_cast", Void.class, cb.literal(filter.getPersonTypes().toArray()).as(PersonTypeEnumType.class), cb.literal("person_type_enum[]"));
                predicates.add(cb.equal(root.get(Person_.personTypes), cb.function("any", Void.class, cb.literal(filter.getPersonTypes().toArray(PersonType[]::new)).as(PersonTypeEnumType.class))));
            }

            return predicates.isEmpty() ? null : cb.and(predicates.toArray(Predicate[]::new));
        };
    }

    /* Private methods */
    private <T> void checkNullElseDo(final T actor, final Consumer<T> consumer) {
        if (actor != null) consumer.accept(actor);
    }

    private void checkEmptyElseDo(final String actor, final Consumer<String> consumer) {
        if (StringUtils.hasText(actor)) consumer.accept(actor);
    }

    private Expression<PersonType[]> anyPersonType(final CriteriaBuilder cb, final Expression<PersonType[]> expression) {
        return cb.function("any", PersonType[].class, expression);
    }

    public static class PersonTypeEnumType extends AbstractArrayType<PersonType[]> {

        public PersonTypeEnumType() {
            super(PersonTypeEnumTypeDescriptor.INSTANCE);
        }

        @Override
        public String getName() {
            return "'person_type_enum[]'";
        }

    }

    public static class PersonTypeEnumTypeDescriptor extends AbstractArrayTypeDescriptor<PersonType[]> {

        public static final PersonTypeEnumTypeDescriptor INSTANCE = new PersonTypeEnumTypeDescriptor();

        private String sqlArrayType;

        public PersonTypeEnumTypeDescriptor() {
            super(PersonType[].class);
        }

        @Override
        protected String getSqlArrayType() {
            return sqlArrayType;
        }

        @Override
        public void setParameterValues(Properties parameters) {
            sqlArrayType = parameters.getProperty(SQL_ARRAY_TYPE);
            super.setParameterValues(parameters);
        }

    }


}
