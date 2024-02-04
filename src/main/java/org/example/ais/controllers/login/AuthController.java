package org.example.ais.controllers.login;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/auth")
public class AuthController {
    @GetMapping
    public String authUserPage(Model model) {
        model.addAttribute("namePage", "auth");
        return "/login/auth";
    }
    @GetMapping("/staff")
    public String authAdminPage(Model model) {
        model.addAttribute("namePage", "auth staff");
        return "/login/auth-staff";
    }
}
