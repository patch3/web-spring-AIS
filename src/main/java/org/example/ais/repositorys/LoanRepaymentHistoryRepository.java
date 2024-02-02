package org.example.ais.repositorys;

import org.example.ais.models.LoanRepaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepaymentHistoryRepository extends JpaRepository<LoanRepaymentHistory, Long> {}
