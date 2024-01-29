package org.example.contosositemaven.repositorys;

import org.example.contosositemaven.models.LoanRequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRequestHistoryRepository extends JpaRepository<LoanRequestHistory, Long> {}
