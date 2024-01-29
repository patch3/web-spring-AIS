package org.example.contosositemaven.models;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;

@Entity
@EnableJpaRepositories
@Table(name = "creditor")
public class Creditor {
    @Column(name = "email")
    public String email;
    @Column(name = "password_hash")
    public String passwordHash;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Creditor() {
    }


    public Creditor(String workEmail, String passwordHash) {
        this.email = workEmail;
        this.passwordHash = passwordHash;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setEmail(String workEmail) {
        this.email = workEmail;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
