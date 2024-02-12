package org.example.ais.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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

    @Column(name = "confirmed", nullable = false)
    private boolean confirmed;

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

    public Client(){}

    public Client(String fullName, String email, boolean confirmed, byte[] passportPhoto, String passwordHash) {
        this.fullName = fullName;
        this.email = email;
        this.confirmed = confirmed;
        this.passportPhoto = passportPhoto;
        this.passwordHash = passwordHash;
    }
}
