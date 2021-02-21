package com.doroshenko.serhey.person.domain.security;

import com.doroshenko.serhey.person.domain.core.base.BaseAuditable;
import com.doroshenko.serhey.person.domain.person.Person;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Database representation of internal user
 *
 * @author Serhey Doroshenko
 * @see BaseAuditable
 */
@Entity
@Table(name = "internal_user")
public class InternalUser extends BaseAuditable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @ManyToMany
    @JoinTable(
            name = "internal_user_to_role",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "role_id", nullable = false)}
    )
    private Set<InternalUserRole> roles; // Lazy getter initialization
    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;
    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;
    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;

    /* Getters and setters */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<InternalUserRole> getRoles() {
        if (roles == null) roles = new HashSet<>();
        return roles;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

}
