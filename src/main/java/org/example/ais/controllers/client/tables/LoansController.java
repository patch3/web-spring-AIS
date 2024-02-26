package org.example.ais.controllers.client.tables;

import jakarta.servlet.http.HttpServletResponse;
import org.example.ais.projections.LoanProjection;
import org.example.ais.repositorys.LoanRepository;
import org.example.ais.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/client/loans")
public class LoansController {
    private final LoanRepository loanRepository;

    private final LoanService loanService;

    @Autowired
    public LoansController(LoanRepository loanRepository, LoanService loanService) {
        this.loanRepository = loanRepository;
        this.loanService = loanService;
    }

    @GetMapping
    public String loansPage(Model model) {
        model.addAttribute("loans", loanRepository.findAll());
        return "/client/tables/loans";
    }

    @RequestMapping
    @PostMapping(value = "/filtered-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LoanProjection> filteredData(
            @RequestParam String columnFilter,
            @RequestParam String patternName,
            @RequestParam Double patternRate,
            @RequestParam Double patternTerm
    ) {
        return loanRepository.findProjectionByNameStartingWithAndInterestRateStartingWithAndTermStartingWith(
                patternName, patternRate, patternTerm, Sort.by(Sort.Direction.ASC, columnFilter)
        );
    }

    @PostMapping("/remove")
    public void removeEntry(@RequestParam Long id) {
        loanRepository.deleteById(id);
    }


}
