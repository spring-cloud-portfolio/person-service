package com.doroshenko.serhey.person.domain.core.base;

import com.doroshenko.serhey.person.domain.core.api.Identifiable;

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

    /**
     * Determine the minimum id value
     */
    public static final int MIN_ID = 1;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @Version
    @Column(name = "version", nullable = false)
    private long version;

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
