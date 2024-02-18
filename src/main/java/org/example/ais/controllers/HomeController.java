package org.example.ais.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "/home"})
public class HomeController {
    @GetMapping
    public String showHomePage(Model model) {
        model.addAttribute("namePage", "home");

        return "home";
    }
}