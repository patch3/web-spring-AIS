package org.example.contosositemaven.services.ClientDetails;

import org.example.contosositemaven.dto.ClientDTO;
import org.example.contosositemaven.models.Client;
import org.example.contosositemaven.repositorys.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Lazy
public class ClientServiceImpl implements ClientDetailsService {
    private final ClientsRepository clientsRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClientServiceImpl(ClientsRepository clientsRepository, PasswordEncoder passwordEncoder) {
        this.clientsRepository = clientsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientsRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Client not found with email: " + email));
        return User
                .withUsername(client.getEmail())
                .password(client.getPasswordHash())
                .roles("USER")
                .build();
    }

    @Override
    public void registerNewClient(ClientDTO clientDTO) {
        Client client = new Client(
                clientDTO.getFullName(),
                clientDTO.getEmail(),
                clientDTO.isConfirmedAccount(),
                clientDTO.getPassportPhoto(),
                passwordEncoder.encode(clientDTO.getPasswordHash())
        );
        client.setEmail(clientDTO.getEmail());
        clientsRepository.save(client);
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        return clientsRepository.findByEmail(email);
    }
}