package org.example.contosositemaven.models;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;
import javax.persistence.*;

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

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Loan getLoan() {
        return loan;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }
}
