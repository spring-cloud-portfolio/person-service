package com.doroshenko.serhey.person.repository.core.util;

import com.doroshenko.serhey.person.enumeration.person.PersonType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.Collection;
import java.util.function.Function;

import static com.doroshenko.serhey.person.repository.core.jpa.HibernateMetadataBuilderContributor.I_LIKE_FN;
import static com.doroshenko.serhey.person.repository.core.jpa.HibernateMetadataBuilderContributor.PERSON_TYPES_CONTAINS_FN;
import static java.util.stream.Collectors.joining;

/**
 * Defines set of utilities, intended to simplify interaction with JPA Criteria API
 *
 * @author Serhey Doroshenko
 */
public final class CriteriaUtils {

    public static final String WILDCARD = "%";

    /**
     * Performs case insensitive comparison.
     * <p>
     * <code>
     * ... where lower(expression) like %parameter
     * </code>
     *
     * @param expression column definition
     * @param parameter  string to compare with
     * @return {@code Function<CriteriaBuilder, Predicate>}
     */
    public static Function<CriteriaBuilder, Predicate> iEndingWith(final Expression<String> expression, final String parameter) {
        return cb -> cb.like(cb.lower(expression), WILDCARD.concat(parameter.strip().toLowerCase()));
    }

    /**
     * Performs case insensitive comparison.
     * <p>
     * <code>
     * ... where lower(expression) like parameter%
     * </code>
     *
     * @param expression column definition
     * @param parameter  string to compare with
     * @return {@code Function<CriteriaBuilder, Predicate>}
     */
    public static Function<CriteriaBuilder, Predicate> iStartingWith(final Expression<String> expression, final String parameter) {
        return cb -> cb.like(cb.lower(expression), parameter.strip().toLowerCase().concat(WILDCARD));
    }

    public static Function<CriteriaBuilder, Predicate> iLikeFn(final Expression<String> expression, final String parameter) {
        // The syntax of spatial functions requires you to test the function against a boolean value in hibernate
        return cb -> cb.isTrue(cb.function(I_LIKE_FN, Boolean.class, expression, cb.literal(parameter)));
    }

    /**
     * Performs custom defined function
     * <p>
     * Determines if personTypes column contains any of given types
     *
     * @param expression person_types column
     * @param types      collection of the person types
     * @return {@code Function<CriteriaBuilder, Predicate>}"
     */
    public static Function<CriteriaBuilder, Predicate> personTypesContainsFn(final Expression<PersonType[]> expression, final Collection<PersonType> types) {
        return cb -> {
            // Extract SQL like array, from PersonType enums
            final String personTypes = types.stream().map(Enum::name).collect(joining(",", "{", "}"));
            // The syntax of spatial functions requires you to test the function against a boolean value in hibernate
            return cb.isTrue(cb.function(PERSON_TYPES_CONTAINS_FN, Boolean.class, expression, cb.literal(personTypes)));
        };
    }

    private CriteriaUtils() {
        // Suppress instantiation
    }

}
