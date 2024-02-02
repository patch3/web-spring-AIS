package org.example.ais.controllers.login;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/auth")
public class AuthController {
    /*
        @GetMapping
        public String showAuthPage(Model model) {
            return "/login/auth";
        }

        public String processAuth(
                @RequestParam String email,
                @RequestParam String password,
                Model model
        ) {
            Client client = clientsRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
            if (client == null || !passwordEncoder.matches(password, client.getPasswordHash())) {
                model.addAttribute("error", "Invalid email or password");
                return "/login/auth";
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(client.getEmail(), null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/home";
        }
        @GetMapping("/admin")
        public String processAuthAdmin(
                @RequestParam String email,
                @RequestParam String password,
                Model model
        ){
            Creditor creditor = creditorRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Creditor not found with email: "+email));
            if (creditor == null || !passwordEncoder.matches(password, creditor.getPasswordHash())) {
                model.addAttribute("error", "Invalid email or password");
                return "/login/auth";
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(creditor.getEmail(), null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/home";
        }*/
    @GetMapping
    public String authUserPage(Model model) {
        model.addAttribute("namePage", "auth");
        return "/login/auth";
    }
    @GetMapping("/admin")
    public String authAdminPage(Model model) {
        model.addAttribute("namePage", "auth staff");
        return "/login/auth";
    }
}
