package org.example.ais.controllers.client;

import lombok.val;
import org.example.ais.repositorys.ClientRepository;
import org.example.ais.repositorys.CreditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/client/profile")
public final class ClientProfileController {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientProfileController(ClientRepository clientRepository, CreditorRepository creditorRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public String showProfilePage(Model model) {
        model.addAttribute("pageName", "Profile");

        val authentication = SecurityContextHolder.getContext().getAuthentication();

        val client = clientRepository.findByEmail(authentication.getName())
                    .orElseThrow(() -> new UsernameNotFoundException(
                            "Client not found with email: " + authentication.getName()));
        model.addAttribute("client", client);

        return "/client/profile";
    }
}
