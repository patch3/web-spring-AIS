package org.example.contosositemaven.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.Set;

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

    public Client(
            String fullName,
            String email,
            boolean confirmedAccount,
            byte[] passportPhoto,
            String passwordHash
    ) {
        this.fullName = fullName;
        this.email = email;
        this.confirmedAccount = confirmedAccount;
        this.passportPhoto = passportPhoto;
        this.passwordHash = passwordHash;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isConfirmedAccount() {
        return confirmedAccount;
    }

    public byte[] getPassportPhoto() {
        return passportPhoto;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setConfirmedAccount(boolean confirmedAccount) {
        this.confirmedAccount = confirmedAccount;
    }

    public void setPassportPhoto(byte[] passportPhoto) {
        this.passportPhoto = passportPhoto;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
