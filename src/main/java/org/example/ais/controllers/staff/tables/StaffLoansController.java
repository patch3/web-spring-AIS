package org.example.ais.controllers.staff.tables;

import org.example.ais.controllers.common.tables.BaseLoansController;
import org.example.ais.models.Loan;
import org.example.ais.projections.LoanProjection;
import org.example.ais.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/staff/loans")
public class StaffLoansController extends BaseLoansController {

    @Autowired
    public StaffLoansController(LoanService loanService) {
        super(loanService);
    }

    @Override
    @GetMapping
    public String initializeBasePage(String error, Model model) {
        model.addAttribute("loans", loanService.findAll());
        return super.initializeBasePage(error, model);
    }


    @PostMapping("/add")
    public String addEntry(@RequestParam Loan loan) {
        loanService.save(loan);
        return "redirect:/staff/loans";
    }

    @PostMapping("/remove")
    public String removeEntry(@RequestParam("loanId") Long loanId) {
        loanService.delete(loanId);
        return "redirect:/staff/loans";
    }


    @GetMapping("/export")
    public ResponseEntity<Resource> export() throws IOException {
        return getResourceResponseEntity(loanService);
    }

    @RequestMapping
    @PostMapping(value = "/filtered-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LoanProjection> filteredData(
            @RequestParam String columnFilter,
            @RequestParam String patternName,
            @RequestParam Float patternRate,
            @RequestParam Integer patternDuration,
            @RequestParam Long patternAmount
    ) {
        return loanService.findProjectionByStartWith(
                patternName, patternDuration, patternRate, patternAmount, Sort.by(Sort.Direction.ASC, columnFilter)
        );
    }

    @Override
    public String getStaticPathToBasePage() {
        return "/staff/tables/loans";
    }
}
