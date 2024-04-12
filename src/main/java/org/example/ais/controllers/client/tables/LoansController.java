package org.example.ais.controllers.client.tables;

import lombok.val;
import org.example.ais.controllers.common.tables.BaseLoansController;
import org.example.ais.projections.LoanProjection;
import org.example.ais.security.ClientDetails;
import org.example.ais.services.LoanRequestHistoryService;
import org.example.ais.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/client/loans")
public class LoansController extends BaseLoansController {

    private final LoanRequestHistoryService loanRequestHistoryService;

    @Autowired
    public LoansController(LoanService loanService, LoanRequestHistoryService loanRequestHistoryService) {
        super(loanService);
        this.loanRequestHistoryService = loanRequestHistoryService;
    }

    @Override
    @GetMapping
    public String initializeBasePage(String error, Model model) {
        model.addAttribute("loans", loanService.findAll());
        return super.initializeBasePage(error, model);
    }

    @Override
    public String getStaticPathToBasePage() {
        return "/client/tables/loans";
    }

    @PostMapping(value = "/take-credit")
    public String takeLoan(@RequestParam Long loanId, Authentication authentication) {
        val principal = authentication.getPrincipal();
        if (!(principal instanceof ClientDetails clientDetails)) {
            return "redirect:/client/tables/loans?error";
        }
        val loan = loanService.findById(loanId);
        if (!loanRequestHistoryService.recordLoanRequest(loan, clientDetails.client())) {
            return "redirect:/client/tables/loans?error";
        }
        return "redirect:/client/tables/loans";
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
}
