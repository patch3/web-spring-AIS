package org.example.ais.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Модель таблицы
 * История выплат кредитов
 */


@Data
@NoArgsConstructor

@Entity
@Table(name = "loan_repayment_history")
@EnableJpaRepositories
public class LoanRepaymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "request_history_id", nullable = false)
    private LoanRequestHistory loanRequestHistory;

    @Temporal(TemporalType.DATE)
    @Column(name = "repayment_data", nullable = false)
    private Date repaymentData;

    @Column(name = "pay", precision = 10, scale = 2, nullable = false)
    private BigDecimal pay;


}
