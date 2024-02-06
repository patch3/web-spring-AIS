package org.example.ais.controllers.staff;

import org.example.ais.repositorys.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/staff/confirmation")
public class ConfirmationController {
    private final ClientRepository clientRepository;

    @Autowired
    public ConfirmationController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public String confirmationPage(Model model) {
        model.addAttribute("namePage", clientRepository.findAll());
        model.addAttribute("clients", clientRepository.findAll());
        return "/staff/confirmation";
    }

    @GetMapping("/photo")
    public ResponseEntity<byte[]> photoPage(@RequestParam(value = "id", required = true) Long id, Model model) {
        model.addAttribute("namePage", "Photo passport");

        byte[] imageData = clientRepository.findImageDataById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }
}
