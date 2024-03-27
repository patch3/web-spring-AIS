package org.example.ais.controllers.staff.tables;

import lombok.val;
import org.example.ais.models.Loan;
import org.example.ais.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

import static org.example.ais.controllers.client.tables.LoansController.getResourceResponseEntity;

@Controller
@RequestMapping("/staff/loan")
public class StaffLoansController {

    private final LoanService loanService;

    @Autowired
    public StaffLoansController( LoanService loanService) {
        this.loanService = loanService;
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
        loanService.save(loan);
    }

    @PostMapping("/remove")
    public void removeEntry(@RequestParam Long id) {
        loanService.delete(id);
    }


    @GetMapping("/export")
    public ResponseEntity<Resource> export() throws IOException {
        return getResourceResponseEntity(loanService);
    }
}
