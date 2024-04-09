package org.example.ais.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ais.projections.ClientProjection;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@EnableJpaRepositories
@Table(name = "client")
public class Client implements IModel, ClientProjection {
    public static final String COLUMN_NAME = "email";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "confirmed", nullable = false)
    @ColumnDefault("false")
    private boolean confirmed;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "passport_photo", nullable = false)
    private byte[] passportPhoto;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<LoanRequestHistory> clientRequestHistory;

    public Client(String fullName,
                  String email,
                  boolean confirmed,
                  byte[] passportPhoto,
                  String passwordHash
    ) {
        this.fullName = fullName;
        this.email = email;
        this.confirmed = confirmed;
        this.passportPhoto = passportPhoto;
        this.passwordHash = passwordHash;
    }

    @Override
    public String getDefaultColumnName() {
        return COLUMN_NAME;
    }
}
