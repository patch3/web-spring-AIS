package org.example.ais.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.ais.dto.client.ClientDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Blob;
import java.util.Set;

@Getter
@Setter

@Entity
@Component
@EnableJpaRepositories
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "confirmed_account", nullable = false)
    private boolean confirmedAccount;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "passport_photo",  columnDefinition="longblob", nullable = false)
    private byte[] passportPhoto;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;



    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<LoanRequestHistory> clientRequestHistory;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<LoanRepaymentHistory> clientRepaymentHistory;

    public Client() {
    }

    public Client(String fullName, String email, boolean confirmedAccount, byte[] passportPhoto, String passwordHash) {
        this.fullName = fullName;
        this.email = email;
        this.confirmedAccount = confirmedAccount;
        this.passportPhoto = passportPhoto;
        this.passwordHash = passwordHash;
    }

    public Client(ClientDTO clientDTO) throws IOException {
        this(
                clientDTO.getFullName(),
                clientDTO.getEmail(),
                clientDTO.isConfirmedAccount(),
                clientDTO.getPassportPhoto().getBytes(),
                clientDTO.getPasswordHash()
        );
    }
}
