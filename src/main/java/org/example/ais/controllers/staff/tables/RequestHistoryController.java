package org.example.ais.controllers.staff.tables;

import org.example.ais.repositorys.LoanRequestHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class RequestHistoryController {
    private final LoanRequestHistoryRepository requestHistoryRepository;

    @Autowired
    public RequestHistoryController(LoanRequestHistoryRepository loanRequestHistoryRepository) {
        this.requestHistoryRepository = loanRequestHistoryRepository;
    }

    public String requestHistoryTablePage(Model model) {
        model.addAttribute("namePage", "Request history");
        model.addAttribute("loans", requestHistoryRepository.findAll());
        return "/staff/tables/request-history";
    }
}