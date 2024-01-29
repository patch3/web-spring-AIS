package org.example.contosositemaven.controllers.login;

import org.example.contosositemaven.models.Client;
import org.example.contosositemaven.repositorys.ClientsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/login/reg")
public class RegController {

    private static final Logger logger = LoggerFactory.getLogger(RegController.class);

    private final ClientsRepository clientsRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegController(ClientsRepository clientsRepository, PasswordEncoder passwordEncoder) {
        this.clientsRepository = clientsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showRegistrationPage(Model model){
        return "/login/reg";
    }


    @PostMapping("/process")
    public String processRegistration(
            @RequestParam(name="fullName") String fullName,
            @RequestParam(name="email") String email,
            @RequestParam(name="password") String password,
            @RequestParam(name="repeatPassword") String repeatPassword,
            @RequestParam(name="passportPhoto") MultipartFile passportPhoto,
            Model model
    ){
        if (!password.equals(repeatPassword)) {
            model.addAttribute("error", "Password do not match");
        }
        try {
            byte[] photoBytes = passportPhoto.getBytes();
            // Хеширование пароля
            String hashedPassword = passwordEncoder.encode(password);

            clientsRepository.save(new Client(fullName, email, false, photoBytes, hashedPassword));

            model.addAttribute("message", "Registration success!");
            return "redirect:/home";
        } catch (IOException ex) {
            ex.printStackTrace();
            model.addAttribute("error", "Error processing passport photo");
            return "redirect:/login/reg";
        }
    }
}
