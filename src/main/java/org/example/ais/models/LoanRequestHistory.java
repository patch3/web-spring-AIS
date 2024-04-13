package org.example.ais.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.util.Set;

/**
 * модель таблицы
 * История запросов всех кредитов
 */
@Data
@NoArgsConstructor

@Entity
@Table(name = "loan_request_history")
@EnableJpaRepositories
public class LoanRequestHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "loans_id", nullable = false)
    private Loan loan;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @ColumnDefault("false")
    @Column(name = "closed", nullable = false)
    private boolean closed;

/*
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loanRequestHistory")
    private Set<LoanRepaymentHistory> loanRepaymentHistory;
*/

    public LoanRequestHistory(Client client, Loan loan) {
        this.client = client;
        this.loan = loan;
        this.data = LocalDate.now();
        this.closed = false;
    }

    public String getInfo() {
        return String.format(
               "%s: %s;",
                data.toString(), loan.toString()
        );
    }
}
