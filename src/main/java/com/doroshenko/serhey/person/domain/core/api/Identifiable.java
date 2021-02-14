package com.doroshenko.serhey.person.domain.core.api;

import javax.persistence.Transient;

import static com.doroshenko.serhey.person.domain.core.base.BaseEntity.MIN_ID;

/**
 * Intended for entities whose id is {@link Long}
 *
 * @author Serhey Doroshenko
 */
public interface Identifiable {

    Long getId();

    void setId(Long id);

    @Transient
    default boolean isNew() {
        return getId() == null || getId() < MIN_ID;
    }

}
