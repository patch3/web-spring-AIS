package org.example.ais.controllers;

import lombok.val;
import org.example.ais.config.constants.RoleConst;
import org.example.ais.repositorys.ClientRepository;
import org.example.ais.repositorys.CreditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

/**
 * страница пользователя
 * только для аунтифицированных пользователей
 * как для админов, так и для пользоватлей
 */

@Controller
@RequestMapping("/profile")
public final class ProfileController {

    private final ClientRepository clientRepository;
    private final CreditorRepository creditorRepository;

    @Autowired
    public ProfileController(ClientRepository clientRepository, CreditorRepository creditorRepository) {
        this.clientRepository = clientRepository;
        this.creditorRepository = creditorRepository;
    }

    @GetMapping
    public String showProfilePage(Model model) {
        model.addAttribute("pageName", "Profile");

        val authentication = SecurityContextHolder.getContext().getAuthentication();
        val granted = authentication.getAuthorities()
                .stream()
                .findFirst()
                .orElse(null);
        assert granted != null;

        if (granted.getAuthority().equals(RoleConst.ADMIN)) {
            val creditor = creditorRepository.findByEmail(authentication.getName());
            model.addAttribute("creditor", creditor);
        } else {
            val client = clientRepository.findByEmail(authentication.getName());
            model.addAttribute("client", client);
        }
        return "/profile";
    }

}
