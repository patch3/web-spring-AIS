package ru.sudrf.ais.accounting.equipment.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.sudrf.ais.accounting.equipment.configs.constants.Role;
import ru.sudrf.ais.accounting.equipment.models.Client;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public record UserDetailsImpl(Client client) implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + Role.USER));
    }

    @Override
    public String getUsername() {
        return client.getLogin();
    }

    @Override
    public String getPassword() {
        return client.getPasswordHash();
    }
}
