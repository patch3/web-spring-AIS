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
public class Loan implements IModel, LoanProjection {

    public static final String COLUMN_NAME = "name";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "duration_in_months", nullable = false)
    private Integer durationInMonths;

    @Column(name = "interest_rate", nullable = false)
    private Float interestRate;

    @Column(name = "amount", nullable = false)
    private Long amount;


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loan")
    private Set<LoanRequestHistory> loanRequestHistories;


    public Loan(Long id,
                String name,
                String description,
                Float interestRate,
                Long amount,
                Integer durationInMonths) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.interestRate = interestRate;
        this.amount = amount;
        this.durationInMonths = durationInMonths;
    }

    public Loan(String name,
                String description,
                Float interestRate,
                Long amount,
                Integer durationInMonths) {
        this.id = null;
        this.name = name;
        this.description = description;
        this.interestRate = interestRate;
        this.amount = amount;
        this.durationInMonths = durationInMonths;
    }

    public Loan(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.description = loan.getDescription();
        this.interestRate = loan.getInterestRate();
        this.amount = loan.getAmount();
        this.durationInMonths = loan.getDurationInMonths();
        this.loanRequestHistories = loan.getLoanRequestHistories();
    }

    @Override
    public String getDefaultColumnName() {
        return COLUMN_NAME;
    }

}
