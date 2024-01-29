package org.example.contosositemaven.services;

import org.example.contosositemaven.models.Creditor;
import org.example.contosositemaven.repositorys.CreditorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class CreditorDetailsServiceImpl implements UserDetailsService {
    private final CreditorsRepository creditorsRepository;
    @Autowired
    public CreditorDetailsServiceImpl(CreditorsRepository creditorsRepository) {
        this.creditorsRepository = creditorsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Creditor creditor = creditorsRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Creditor not found with email: "+email));
        return User
                .withUsername(creditor.getEmail())
                .password(creditor.getPasswordHash())
                .roles("EMPLOYEE")
                .build();
    }
}
