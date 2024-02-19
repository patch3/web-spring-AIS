package org.example.ais.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.util.Date;

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
    @Column(name = "repayment_data")
    private Date repaymentData;

    @Column(name = "pay", precision = 10, scale = 2, nullable = false)
    private BigDecimal pay;

    public LoanRepaymentHistory(LoanRequestHistory loanRequestHistory,
                                Date repaymentData,
                                BigDecimal pay) {
        this.loanRequestHistory = loanRequestHistory;
        this.repaymentData = repaymentData;
        this.pay = pay;
    }
}
