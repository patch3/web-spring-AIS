package org.example.ais.repositorys;

import org.example.ais.models.LoanRequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRequestHistoryRepository extends JpaRepository<LoanRequestHistory, Long> {
}
