package org.example.contosositemaven.services.ClientDetails;

import org.example.contosositemaven.dto.ClientDTO;
import org.example.contosositemaven.models.Client;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface ClientDetailsService extends UserDetailsService {
    void registerNewClient(ClientDTO clientDTO);
    Optional<Client> findByEmail(String email);
}
