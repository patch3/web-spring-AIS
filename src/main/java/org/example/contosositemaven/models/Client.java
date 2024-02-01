package org.example.contosositemaven.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.Set;

@Getter
@Setter

@Entity
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

    @Column(name = "passport_photo", nullable = false)
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
}
