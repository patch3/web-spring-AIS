package ru.sudrf.ais.accounting.equipment.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.sudrf.ais.accounting.equipment.models.Client;

import java.util.Collection;
import java.util.List;

public record UserDetailsImpl(Client client) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }
}
