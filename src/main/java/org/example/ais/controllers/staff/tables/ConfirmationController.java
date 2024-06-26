package org.example.ais.controllers.staff.tables;

import org.example.ais.projections.ClientProjection;
import org.example.ais.repositorys.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/staff/confirmation")
public class ConfirmationController {
    private final ClientRepository clientRepository;

    @Autowired
    public ConfirmationController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public String confirmationPage(Model model) {
        model.addAttribute("namePage", "confirmation");
        model.addAttribute(
                "clients",
                clientRepository.findByConfirmedFalse(
                        Sort.by(
                                Sort.Direction.ASC, "fullName"
                        )
                )
        );
        return "/staff/tables/confirmation";
    }

    @PostMapping("/accept")
    @Transactional(readOnly = true)
    public String acceptEntryClient(@RequestParam(name = "id") Long id) {
        clientRepository.confirmClientById(id);
        return "redirect:/staff/confirmation";
    }

    @PostMapping("/reject")
    public String rejectEntryClient(@RequestParam(name = "id") Long id) {
        clientRepository.deleteById(id);
        return "redirect:/staff/confirmation";
    }

    @ResponseBody
    @Transactional(readOnly = true)
    @PostMapping(value = "/filtered-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientProjection> filteredData(
            @RequestParam String columnFilter,
            @RequestParam String patternFullName,
            @RequestParam String patternEmail
    ) {
        return clientRepository.findProjectionByFullNameStartingWithAndEmailStartingWithAndConfirmedFalse(
                patternFullName, patternEmail, Sort.by(Sort.Direction.ASC, columnFilter)
        );
    }
}