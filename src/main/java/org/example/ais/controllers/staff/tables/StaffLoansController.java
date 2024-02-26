package org.example.ais.controllers.staff.tables;

import lombok.val;
import org.example.ais.models.Loan;
import org.example.ais.repositorys.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/staff/loan")
public class StaffLoansController {

    private final LoanRepository loanRepository;

    @Autowired
    public StaffLoansController(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @GetMapping
    public String staffLoansPage(Model model) {
        model.addAttribute("namePage", "Staff loans table");
        return "/staff/tables/loans";
    }

    @PostMapping("/add")
    public void addEntry(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Double interestRate,
            @RequestParam Double term
    ) {
        val loan = new Loan(name, description, interestRate, term);
        loanRepository.save(loan);
    }

    @PostMapping("/remove")
    public void removeEntry(@RequestParam Long id) {
        loanRepository.deleteById(id);
    }
}
