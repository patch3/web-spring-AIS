package org.example.ais.controllers.staff;

import org.example.ais.repositorys.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff/confirmation")
public class ConfirmationController {
    private final LoanRepository loanRepository;

    @Autowired
    public ConfirmationController(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @GetMapping
    public String confirmationPage() {
        return "/staff/confirmation";
    }
}
