package org.example.ais.dto.client;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Data
@Component
public class ClientDTO {

    protected String fullName;

    protected String email;

    protected boolean confirmedAccount;

    protected MultipartFile passportPhoto;

    protected String password;
}
