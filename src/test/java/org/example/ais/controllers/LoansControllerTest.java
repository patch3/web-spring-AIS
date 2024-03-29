package org.example.ais.controllers;

import org.example.ais.controllers.client.tables.LoansController;
import org.example.ais.models.Loan;
import org.example.ais.projections.LoanProjection;
import org.example.ais.services.LoanService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoansControllerTest {
    @Mock
    private LoanService loanService;
    @Mock
    private Model model;
    @InjectMocks
    private LoansController loansController;

    @Test
    public void testLoansPage() {
        List<Loan> loans = List.of(new Loan(0L, "test", "test", 0.f, 0.d, 0));
        when(loanService.findAll()).thenReturn(loans);

        String viewName = loansController.loansPage(model);

        assertEquals(viewName, "/client/tables/loans");

        verify(model).addAttribute("loans", loans);
    }

    @Test
    public void testFilteredData() {
        String columnFilter = Loan.COLUMN_NAME;
        String patternName = "test2";
        Integer patternDuration = null;
        Float patternRate = null;
        Long patternAmount = null;

        List<LoanProjection> expected = List.of(
                new Loan(0L, "test1", "test1", 0.f, 0.d, 0),
                new Loan(1L, "test2", "test2", 0.f, 0.d, 0),
                new Loan(2L, "test3", "test3", 0.f, 0.d, 0)
        );

        when(loanService.findProjectionByStartWith(
                patternName, patternDuration, patternRate, patternAmount, Sort.by(Sort.Direction.ASC, columnFilter)
        )).thenReturn(expected);

        List<LoanProjection> actual = loansController.filteredData(columnFilter, patternName, patternRate, patternDuration, patternAmount);

        assertEquals(expected, actual);
    }

    @Test
    public void testRemoveEntry() {
        Long id = 1L;

        loansController.removeEntry(id);

        verify(loanService).delete(id);
    }

    @Test
    public void testExport() throws IOException {
        ClassPathResource resource = new ClassPathResource("org/example/ais/controllers/file.docx");
        byte[] expectedBytes = Files.readAllBytes(Paths.get(resource.getURI()));

        when(loanService.exportToExcel()).thenReturn(expectedBytes);

        ResponseEntity<Resource> response = loansController.export();

        assertNotNull(response);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
