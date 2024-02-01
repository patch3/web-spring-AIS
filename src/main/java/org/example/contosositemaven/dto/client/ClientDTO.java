package org.example.contosositemaven.dto.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Setter
@Getter
public class ClientDTO {
    protected Long id;
    protected String fullName;
    protected String email;
    protected boolean confirmedAccount;
    protected MultipartFile passportPhoto;
    protected String password;
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    public ClientDTO(Long id,
                     String fullName,
                     String email,
                     boolean confirmedAccount,
                     MultipartFile passportPhoto,
                     String password) throws IOException {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.confirmedAccount = confirmedAccount;
        this.passportPhoto = passportPhoto;
        this.password = password;
    }
    public String getPasswordHash(){
        return passwordEncoder.encode(password);
    }
}
