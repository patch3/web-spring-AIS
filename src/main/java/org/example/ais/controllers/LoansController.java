package org.example.ais.controllers;

import org.example.ais.repositorys.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loans")
public class LoansController {
    private final LoanRepository loanRepository;

    @Autowired
    public LoansController(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @GetMapping
    public String loansPage(Model model) {
        model.addAttribute("loans", loanRepository.findAll());
        return "/loans";
    }
}
