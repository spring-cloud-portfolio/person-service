package com.doroshenko.serhey.person.domain.core.base;

import com.doroshenko.serhey.person.dto.core.api.Identifiable;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Base implementation of {@link Identifiable}
 * Also features a {@link #version} field that allows enable the optimistic locking mechanism by default
 *
 * @author Serhey Doroshenko
 * @see Identifiable
 */
@MappedSuperclass
public abstract class BaseEntity implements Identifiable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Version
    @Column(name = "version", nullable = false)
    private long version;

    /* Utility methods */
    @Override
    @Transient
    public boolean isNew() {
        return id == null;
    }

    /* Identifiable implementation */
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /* Getter */
    public long getVersion() {
        return version;
    }

}
