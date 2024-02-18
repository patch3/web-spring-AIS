package org.example.ais.services;

import lombok.val;
import org.example.ais.config.constants.RoleConst;
import org.example.ais.models.Creditor;
import org.example.ais.repositorys.CreditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Lazy
public final class CreditorDetailsServiceImpl implements UserDetailsService {
    private final CreditorRepository creditorRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CreditorDetailsServiceImpl(CreditorRepository creditorRepository, PasswordEncoder passwordEncoder) {
        this.creditorRepository = creditorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        val creditor = creditorRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Creditor not found with email: " + email));
        return User
                .withUsername(creditor.getEmail())
                .password(creditor.getPasswordHash())
                .roles(RoleConst.ADMIN)
                .build();
    }

    public void register(String email, String password) {
        if (creditorRepository.countByEmail(email) > 0) return;

        String encodedPassword = passwordEncoder.encode(password);
        val creditor = new Creditor(email, encodedPassword);
        creditorRepository.save(creditor);
    }
}
