package org.example.ais.controllers.client.login;

import lombok.val;
import org.example.ais.dto.client.ClientRegistrationDTO;
import org.example.ais.services.client.ClientDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/client/login/reg")
public final class RegController {
    private final ClientDetailService clientDetailService;

    @Autowired
    public RegController(ClientDetailService clientDetailService) {
        this.clientDetailService = clientDetailService;
    }


    @GetMapping
    public String showRegistrationPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("namePage", "registration");

        if (error != null) {
            model.addAttribute("errorMessage", "registration failed");
        }
        return "/client/login/reg";
    }

    @ModelAttribute("client")
    public ClientRegistrationDTO userRegistrationDTO() {
        return new ClientRegistrationDTO();
    }


    private static final Pattern namePattern = Pattern.compile("^[a-zA-Zа-яА-Я]+\\s+[a-zA-Zа-яА-Я]+.*", Pattern.CASE_INSENSITIVE);
    private static final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

    @PostMapping("/process")
    public String processRegistration(
            @ModelAttribute("client") ClientRegistrationDTO clientRegistrationDTO
    ) {
        val fullName = clientRegistrationDTO.getFullName();
        val email = clientRegistrationDTO.getEmail();
        val password = clientRegistrationDTO.getPassword();
        val repeatPassword = clientRegistrationDTO.getRepeatPassword();
        val passportPhoto = clientRegistrationDTO.getPassportPhoto();

        if (email == null || repeatPassword == null || password == null || fullName == null || passportPhoto == null) {
            return "redirect:/client/login/reg?error";
        } if (email.isEmpty() || repeatPassword.isEmpty() || password.isEmpty() || fullName.isEmpty() || passportPhoto.isEmpty()) {
            return "redirect:/client/login/reg?error";
        } if (!clientRegistrationDTO.passwordIsEquals()) {
            return "redirect:/client/login/reg?error";
        } if (!namePattern.matcher(fullName).matches() || fullName.length() < 5 || fullName.length() > 32) {
            return "redirect:/client/login/reg?error";
        } if (!emailPattern.matcher(email).matches()) {
            return "redirect:/client/login/reg?error";
        } if (passportPhoto.getSize() > 1024 * 1024) { // Например, размер файла не должен превышать 1 МБ
            return "redirect:/client/login/reg?error";
        }

        try {
            clientDetailService.save(clientRegistrationDTO);
            return "redirect:/home?success=reg";
        } catch (IOException ex) {
            return "redirect:/client/login/reg?error";
        }
    }
}
