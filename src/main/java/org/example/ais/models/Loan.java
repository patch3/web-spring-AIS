package org.example.ais.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jakarta.persistence.*;
import java.util.Set;


@Getter
@Setter

@Entity
@EnableJpaRepositories
@Table(name = "loan")
public class Loan extends Creditor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "interest_rate", nullable = false)
    private double interestRate;
    @Column(name = "loan_term", nullable = false)
    private double loanTerm;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loan")
    private Set<LoanRequestHistory> loanRequestHistory;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loan")
    private Set<LoanRepaymentHistory> loanRepaymentHistories;

    public Loan() {
    }

    public Loan(
            String name,
            String description,
            double interestRate,
            double loanTerm
    ) {
        this.name = name;
        this.description = description;
        this.interestRate = interestRate;
        this.loanTerm = loanTerm;
    }
}
