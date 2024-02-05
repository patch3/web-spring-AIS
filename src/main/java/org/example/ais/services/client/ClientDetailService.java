package org.example.ais.services.client;

import org.example.ais.dto.client.ClientDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

public interface ClientDetailService extends UserDetailsService {
    void save(ClientDTO clientDTO) throws IOException;
}
