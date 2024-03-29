package org.example.ais.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/", "/home"})
public class HomeController {
    @GetMapping
    public String showHomePage(@RequestParam(required = false) String success, Model model) {
        model.addAttribute("namePage", "home");
        if (success != null) {
            switch (success) {
                case "reg":
                    model.addAttribute("successMessage",
                            "Successful registration. Wait for confirmation of the entered data");
                    break;
                case "auth":
                    model.addAttribute("successMessage", "Successful authorization.");
                    break;
            }
        }
        return "home";
    }
}