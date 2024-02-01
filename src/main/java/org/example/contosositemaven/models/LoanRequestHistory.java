package org.example.contosositemaven.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;
import javax.persistence.*;

@Getter
@Setter

@Entity
@Table(name="loan_request_history")
@EnableJpaRepositories
public class LoanRequestHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "loans_id", nullable = false)
    private Loan loan;

    @Column(name = "data_time", nullable = false)
    private Date dataTime;

    public LoanRequestHistory() {}

    public LoanRequestHistory(
            Client client,
            Loan loan,
            Date dataTime
    ) {
        this.client = client;
        this.loan = loan;
        this.dataTime = dataTime;
    }
}
