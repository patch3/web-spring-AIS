package org.example.ais.controllers.client.login;

import org.example.ais.dto.client.ClientRegistrationDTO;
import org.example.ais.services.client.ClientDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/client/login/reg")
public final class RegController {
    private final ClientDetailService clientDetailService;

    @Autowired
    public RegController(ClientDetailService clientDetailService) {
        this.clientDetailService = clientDetailService;
    }


    @GetMapping
    public String showRegistrationPage(Model model) {
        model.addAttribute("namePage", "registration");
        return "/client/login/reg";
    }

    @ModelAttribute("client")
    public ClientRegistrationDTO userRegistrationDTO() {
        return new ClientRegistrationDTO();
    }

    @PostMapping("/process")
    public String processRegistration(
            @ModelAttribute("client") ClientRegistrationDTO clientRegistrationDTO,
            Model model
    ) {
        if (!clientRegistrationDTO.passwordIsEquals()) {
            model.addAttribute("error", "Password do not match");
            return "redirect:/client/login/reg";
        }
        try {
            clientDetailService.save(clientRegistrationDTO);
            model.addAttribute("message", "Registration success!");
            return "redirect:/home";
        } catch (IOException ex) {
            model.addAttribute("error", "Error processing passport photo");
            return "redirect:/client/login/reg";
        }
    }
}
