package com.doroshenko.serhey.person.core.util;

import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.function.Consumer;

import static java.util.Objects.nonNull;

public final class ConditionUtils {

    public static <T> void doIfNonNull(final T actor, final Consumer<T> action) {
        if (nonNull(actor)) action.accept(actor);
    }

    public static void doIfHasText(final String actor, final Consumer<String> action) {
        if (StringUtils.hasText(actor)) action.accept(actor);
    }

    public static <T> void doIfNonEmpty(final Collection<T> actor, final Consumer<Collection<T>> action) {
        if (nonNull(actor) && !actor.isEmpty()) action.accept(actor);
    }

    private ConditionUtils() {
        // Suppress instantiation
    }

}
