package org.example.ais.services.client;

import org.example.ais.dto.client.ClientRegistrationDTO;
import org.example.ais.models.Client;
import org.example.ais.repositorys.ClientRepository;
import org.example.ais.security.ClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@Lazy
public final class ClientDetailsServiceImpl implements ClientDetailService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientDetailsServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Client not found with email: " + email));
        return new ClientDetails(client);
    }

    @Override
    public void save(ClientRegistrationDTO clientDTO) throws IOException {
        clientRepository.save(new Client(clientDTO));
    }
}