package org.example.ais.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Data
@NoArgsConstructor

@Entity
@EnableJpaRepositories
@Table(name = "creditor")
public class Creditor implements IModel {
    public static final String COLUMN_NAME = "email";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;


    public Creditor(String workEmail, String passwordHash) {
        this.email = workEmail;
        this.passwordHash = passwordHash;
    }

    @Override
    public String getDefaultColumnName() {
        return COLUMN_NAME;
    }
}
