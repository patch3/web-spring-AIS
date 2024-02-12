package org.example.ais.security;


import org.example.ais.config.constants.RoleConst;
import org.example.ais.models.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public record ClientDetails(Client client) implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList( new SimpleGrantedAuthority("ROLE_" + RoleConst.USER) );
    }

    @Override
    public String getPassword() {
        return client.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return client.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        //return client.isConfirmedAccount();
        return client.isConfirmed();
    }
}
