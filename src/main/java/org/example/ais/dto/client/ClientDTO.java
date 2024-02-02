package org.example.ais.dto.client;

import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ClientDTO {

    protected String fullName;

    protected String email;

    protected boolean confirmedAccount;

    protected MultipartFile passportPhoto;

    protected String password;

    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

/*    public ClientDTO(String fullName,
                     String email,
                     boolean confirmedAccount,
                     MultipartFile passportPhoto,
                     String password) throws IOException {
        this.fullName = fullName;
        this.email = email;
        this.confirmedAccount = confirmedAccount;
        this.passportPhoto = passportPhoto;
        this.password = password;
    }*/


    public String getPasswordHash(){
        return passwordEncoder.encode(password);
    }
}
