package org.example.ais.controllers.staff.login;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/staff/login/auth")
public final class StaffAuthController {
    @GetMapping
    public String authClientPage(
            @RequestParam(name = "error", required = false, defaultValue = "false") boolean error,
            Model model
    ) {
        model.addAttribute("namePage", "Auth creditor");
        if (error) model.addAttribute("errorMessage", "authorization failed");
        return "/staff/login/auth";
    }
}
