package org.example.contosositemaven.controllers.login;

import org.example.contosositemaven.dto.client.ClientRegistrationDTO;
import org.example.contosositemaven.models.Client;
import org.example.contosositemaven.repositorys.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/login/reg")
public class RegController {

    private static final Logger logger = LoggerFactory.getLogger(RegController.class);

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegController(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showRegistrationPage(Model model){
        return "/login/reg";
    }


    @PostMapping("/process")
    public String processRegistration(
            @ModelAttribute("client") ClientRegistrationDTO clientRegistrationDTO,
            Model model
    ){
        if (!clientRegistrationDTO.passwordIsEquals()) {
            model.addAttribute("error", "Password do not match");
        }
        try {
            byte[] photoBytes = clientRegistrationDTO.getPassportPhoto().getBytes();

            clientRepository.save(
                    new Client(
                            clientRegistrationDTO.getFullName(),
                            clientRegistrationDTO.getEmail(),
                            false,
                            photoBytes,
                            clientRegistrationDTO.getPasswordHash()
                    )
            );

            model.addAttribute("message", "Registration success!");
            return "redirect:/home";
        } catch (IOException ex) {
            ex.printStackTrace();
            model.addAttribute("error", "Error processing passport photo");
            return "redirect:/login/reg";
        }
    }
}
