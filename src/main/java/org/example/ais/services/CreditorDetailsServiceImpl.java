package org.example.ais.services;

import org.example.ais.config.constants.RoleConst;
import org.example.ais.models.Creditor;
import org.example.ais.repositorys.CreditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Lazy
public final class CreditorDetailsServiceImpl implements UserDetailsService {
    private final CreditorRepository creditorRepository;
    @Autowired
    public CreditorDetailsServiceImpl(CreditorRepository creditorRepository) {
        this.creditorRepository = creditorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Creditor creditor = creditorRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Creditor not found with email: "+email));
        return User
                .withUsername(creditor.getEmail())
                .password(creditor.getPasswordHash())
                .roles(RoleConst.ADMIN)
                .build();
    }
}
