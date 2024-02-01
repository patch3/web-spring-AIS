package org.example.contosositemaven.services;

import org.example.contosositemaven.config.constants.RoleConst;
import org.example.contosositemaven.models.Client;
import org.example.contosositemaven.repositorys.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Lazy
public class ClientDetailsServiceImpl implements UserDetailsService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientDetailsServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Client not found with email: " + email));
        return User
                .withUsername(client.getEmail())
                .password(client.getPasswordHash())
                .roles(RoleConst.USER)
                .build();
    }

}