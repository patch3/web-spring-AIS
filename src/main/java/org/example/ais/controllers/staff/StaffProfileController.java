package org.example.ais.controllers.staff;

import lombok.val;
import org.example.ais.models.Creditor;
import org.example.ais.repositorys.CreditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff/profile")
public final class StaffProfileController {
    private final CreditorRepository creditorRepository;

    @Autowired
    public StaffProfileController(CreditorRepository creditorRepository) {
        this.creditorRepository = creditorRepository;
    }

    @GetMapping
    public String showProfilePage(Model model) {
        model.addAttribute("pageName", "Profile creditor");

        val authentication = SecurityContextHolder.getContext().getAuthentication();

        Creditor creditor = creditorRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Creditor not found with email: " + authentication.getName()));
        model.addAttribute("creditor", creditor);

        return "/staff/profile";
    }
}
