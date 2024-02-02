package org.example.ais.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import jakarta.persistence.*;
import java.util.Date;

@Getter
@Setter

@Entity
@Table(name = "loan_repayment_history")
@EnableJpaRepositories
public class LoanRepaymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "loans_id", nullable = false)
    private Loan loan;

    @Column(name = "repayment_data")
    private Date repaymentData;

    public LoanRepaymentHistory() {}

    public LoanRepaymentHistory(Client client, Loan loan, Date repaymentData) {
        this.client = client;
        this.loan = loan;
        this.repaymentData = repaymentData;
    }
}
