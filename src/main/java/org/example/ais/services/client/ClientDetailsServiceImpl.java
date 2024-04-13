package org.example.ais.services.client;

import org.example.ais.dto.client.ClientDTO;
import org.example.ais.models.Client;
import org.example.ais.repositorys.ClientRepository;
import org.example.ais.security.ClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


@Service
@Lazy
public class ClientDetailsServiceImpl implements ClientDetailService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public ClientDetailsServiceImpl(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Client not found with email: " + email));
        return new ClientDetails(client);
    }


    @Override
    public void save(ClientDTO clientDTO) throws IOException {
        clientRepository.save(
                new Client(
                        clientDTO.getFullName(),
                        clientDTO.getEmail(),
                        false,
                        clientDTO.getPassportPhoto().getBytes(),
                        passwordEncoder.encode(clientDTO.getPassword())
                )
        );
    }
}