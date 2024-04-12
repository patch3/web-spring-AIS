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

    public boolean recordLoanRequest(Loan loan, Client client) {
        if (!loanRequestHistoryRepository.existsByLoanAndClient(loan, client)) {
            val loanRequestHistory = new LoanRequestHistory(client, loan);
            this.loanRequestHistoryRepository.save(loanRequestHistory);
            return true;
        }
        return false;
    }

    public void closeById(Long requestId) {
        LoanRequestHistory loanRequestHistory = loanRequestHistoryRepository.findById(requestId)
                .orElseThrow(() -> new IllegalStateException("Loan request not found."));
        loanRequestHistory.setClosed(true);
        loanRequestHistoryRepository.save(loanRequestHistory);
    }
}
