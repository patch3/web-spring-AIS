package org.example.ais.controllers.client.tables;

import org.example.ais.projections.LoanProjection;
import org.example.ais.repositorys.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/client/loans")
public class LoansController {
    private final LoanRepository loanRepository;

    @Autowired
    public LoansController(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @GetMapping
    public String loansPage(Model model) {
        model.addAttribute("loans", loanRepository.findAll());
        return "/client/tables/loans";
    }

    @RequestMapping
    @PostMapping(value = "/filtered-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LoanProjection> filteredData(
            @RequestParam(name = "column") String column,
            @RequestParam(name = "patternName") String patternName,
            @RequestParam(name = "patternInterestRate") Double patternInterestRate,
            @RequestParam(name = "patternTerm") Double patternTerm
    ) {
        return loanRepository.findProjectionByNameStartingWithAndInterestRateStartingWithAndTermStartingWith(
                patternName, patternInterestRate, patternTerm, Sort.by(Sort.Direction.ASC, column)
        );
    }
}
