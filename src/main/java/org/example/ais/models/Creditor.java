package org.example.ais.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jakarta.persistence.*;

@Getter
@Setter

@Entity
@EnableJpaRepositories
@Table(name = "creditor")
public class Creditor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email")
    public String email;

    @Column(name = "password_hash")
    public String passwordHash;

    public Creditor() {
    }

    public Creditor(String workEmail, String passwordHash) {
        this.email = workEmail;
        this.passwordHash = passwordHash;
    }
}
