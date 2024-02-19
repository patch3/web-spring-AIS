package org.example.ais.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.ais.projections.ClientProjection;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Set;

@Data
@NoArgsConstructor

@Entity
@EnableJpaRepositories
@Table(name = "client")
public class Client implements ClientProjection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "confirmed", nullable = false)
    private boolean confirmed;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "passport_photo", columnDefinition = "longblob", nullable = false)
    private byte[] passportPhoto;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private Set<LoanRequestHistory> clientRequestHistory;

    public Client(String fullName, String email, boolean confirmed, byte[] passportPhoto, String passwordHash) {
        this.fullName = fullName;
        this.email = email;
        this.confirmed = confirmed;
        this.passportPhoto = passportPhoto;
        this.passwordHash = passwordHash;
    }
}
