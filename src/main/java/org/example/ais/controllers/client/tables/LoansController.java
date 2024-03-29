package org.example.ais.controllers.client.tables;

import org.example.ais.projections.LoanProjection;
import org.example.ais.services.LoanService;
import org.hibernate.type.NullType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
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
import java.util.List;

@Controller
@RequestMapping("/client/loans")
public class LoansController {
    private final LoanService loanService;

    @Autowired
    public LoansController(LoanService loanService) {
        this.loanService = loanService;
    }

    public static ResponseEntity<Resource> getResourceResponseEntity(LoanService loanService) throws IOException {
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

    @GetMapping
    public String loansPage(
            @RequestParam(value = "error", required = false) NullType error,
            Model model
    ) {

        model.addAttribute("loans", loanService.findAll());
        return "/client/tables/loans";
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

    @PostMapping("/remove")
    public void removeEntry(@RequestParam Long id) {
        loanService.delete(id);
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> export() throws IOException {
        return getResourceResponseEntity(loanService);
    }
}
