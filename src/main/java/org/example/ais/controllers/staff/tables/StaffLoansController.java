package org.example.ais.controllers.staff.tables;

import lombok.val;
import org.example.ais.models.Loan;
import org.example.ais.repositorys.LoanRepository;
import org.example.ais.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

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
        loanService.deleteById(id);
    }


    @GetMapping("/export")
    public ResponseEntity<Resource> export() throws IOException {
        byte[] data = loanService.exportToExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=data.xlsx");

        Resource resource = new ByteArrayResource(data);

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(data.length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}
