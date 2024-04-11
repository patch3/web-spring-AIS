package org.example.ais.services;

import lombok.val;
import org.example.ais.models.Client;
import org.example.ais.models.Loan;
import org.example.ais.models.LoanRequestHistory;
import org.example.ais.repositorys.LoanRequestHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanRequestHistoryService {

    private final LoanRequestHistoryRepository loanRequestHistoryRepository;

    @Autowired
    public LoanRequestHistoryService(LoanRequestHistoryRepository loanRequestHistoryRepository) {
        this.loanRequestHistoryRepository = loanRequestHistoryRepository;
    }

    public void recordLoanRequest(Loan loan, Client client) {
        val loanRequestHistory = new LoanRequestHistory(client, loan);
        this.loanRequestHistoryRepository.save(loanRequestHistory);
    }

}
