package org.example.contosositemaven.controllers.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/profile")
public class ProfileController {
    @GetMapping
    public String showProfilePage() {
        return "/login/profile";
    }
}
