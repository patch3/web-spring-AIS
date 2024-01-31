package org.example.contosositemaven.controllers.login;


import org.example.contosositemaven.repositorys.ClientRepository;
import org.example.contosositemaven.repositorys.CreditorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/auth")
public class AuthController {

    private final ClientRepository clientRepository;

    private final CreditorRepository creditorRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public AuthController(ClientRepository clientRepository, CreditorRepository creditorRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.creditorRepository = creditorRepository;
        this.passwordEncoder = passwordEncoder;
    }
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
    public String showAuthUserPage(Model model) {
        return "/login/auth";
    }
    @GetMapping("/admin")
    public String showAuthAdminPage(Model model) {
        return "/login/auth";
    }

}
