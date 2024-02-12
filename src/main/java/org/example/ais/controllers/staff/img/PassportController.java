package org.example.ais.controllers.staff.img;

import lombok.val;
import org.example.ais.repositorys.ClientRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/staff/img/passport/{id}")
public final class PassportController {
    private final ClientRepository clientRepository;

    public PassportController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public ResponseEntity<byte[]> passportPhotoShow(@PathVariable(value = "id") Long id) {
        val photoOptional = clientRepository.findPassportPhotoById(id);
        return photoOptional
                .map(photo -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.IMAGE_JPEG);
                    return new ResponseEntity<>(photo, headers, HttpStatus.OK);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
