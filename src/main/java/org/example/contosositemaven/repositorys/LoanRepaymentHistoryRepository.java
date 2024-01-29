package org.example.contosositemaven.repositorys;

import org.example.contosositemaven.models.LoanRepaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepaymentHistoryRepository extends JpaRepository<LoanRepaymentHistory, Long> {}
