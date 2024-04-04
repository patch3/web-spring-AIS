package org.example.ais.controllers.common.tables;

import org.example.ais.projections.LoanProjection;
import org.example.ais.services.LoanService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public abstract class BaseLoansController implements InitializeBasePage {

    protected final LoanService loanService;

    public BaseLoansController(LoanService loanService) {
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
