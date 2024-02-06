package org.example.ais.controllers.login;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login/auth")
public class AuthController {
    @GetMapping
    public String authClientPage(
            @RequestParam(name = "error", required = false,  defaultValue = "false")boolean error,
            Model model
    ) {
        model.addAttribute("namePage", "auth client");
        if (error)
            model.addAttribute("errorMessage", "authorization failed");
        return "/login/auth";
    }

    @GetMapping("/staff")
    public String authAdminPage(Model model) {
        model.addAttribute("namePage", "auth staff");
        return "/login/auth-staff";
    }
}
