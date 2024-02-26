package org.example.ais.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ais.projections.LoanProjection;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Set;


@Data
@NoArgsConstructor

@Entity
@EnableJpaRepositories
@Table(name = "loan")
public class Loan implements LoanProjection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;
    @Column(name = "interest_rate", nullable = false)
    private Double interestRate;
    @Column(name = "term", nullable = false)
    private Double term;


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loan")
    private Set<LoanRequestHistory> loanRequestHistories;


    public Loan(String name, String description, Double interestRate, Double term) {
        this.name = name;
        this.description = description;
        this.interestRate = interestRate;
        this.term = term;
    }

    public Loan(String name, Double interestRate, Double term) {
        this.name = name;
        this.interestRate = interestRate;
        this.term = term;
    }
}
