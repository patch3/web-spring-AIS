package org.example.ais.services.client;

import org.example.ais.dto.client.ClientRegistrationDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

public interface ClientDetailService extends UserDetailsService {
    void save(ClientRegistrationDTO clientDTO) throws IOException;
}
