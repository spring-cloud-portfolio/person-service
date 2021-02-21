package com.doroshenko.serhey.person.domain.security;

import com.doroshenko.serhey.person.domain.core.base.BaseAuditable;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;
import static org.hibernate.annotations.FetchMode.SUBSELECT;

/**
 * Database representation of user role
 *
 * @author Serhey Doroshenko
 * @see BaseAuditable
 */
@Entity
@Table(name = "internal_user_role")
public class InternalUserRole extends BaseAuditable {

    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "description")
    private String description;
    @Fetch(SUBSELECT)
    @ManyToMany(fetch = LAZY)
    @JoinTable(
            name = "internal_user_role_to_authority",
            joinColumns = {@JoinColumn(name = "role_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", nullable = false)}
    )
    private Set<InternalUserRoleAuthority> authorities; // Lazy getter initialization

    /* Getters and setters */
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<InternalUserRoleAuthority> getAuthorities() {
        if (authorities == null) authorities = new HashSet<>();
        return authorities;
    }

}
