package com.doroshenko.serhey.person.domain.security;

import com.doroshenko.serhey.person.domain.core.base.BaseAuditable;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Optional;

/**
 * Database representation of user authority
 *
 * @author Serhey Doroshenko
 * @see BaseAuditable
 */
@Entity
@Table(name = "internal_user_role_authority")
public class InternalUserRoleAuthority extends BaseAuditable implements GrantedAuthority {

    @Column(name = "authority", nullable = false)
    private String authority;
    @Column(name = "description")
    private String description;

    /* GrantedAuthority implementation */
    @Override
    public String getAuthority() {
        return authority;
    }

    /* Getters and setters */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
