package org.example.ais.controllers.staff.tables;

import org.example.ais.repositorys.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("namePage", clientRepository.findAll());
        model.addAttribute("clients", clientRepository.findByConfirmedFalse());
        return "/staff/tables/confirmation";
    }

    @PostMapping("/accept")
    public String acceptEntryClient(
            @RequestParam(name="id") Long id,
            Model model
    ) {
        clientRepository.confirmClientById(id);
        return "redirect:/staff/confirmation";
    }

    @PostMapping("/reject")
    public String rejectEntryClient(
            @RequestParam(name = "id") Long id,
            Model model
    ) {
        clientRepository.deleteById(id);
        return "redirect:/staff/confirmation";
    }
}
