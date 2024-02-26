package org.example.ais.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;
import java.util.Set;

/**
 * модель таблицы
 * История всех кредитов
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

    @Temporal(TemporalType.DATE)
    @Column(name = "data", nullable = false)
    private Date data;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loanRequestHistory")
    private Set<LoanRepaymentHistory> loanRepaymentHistory;

}
