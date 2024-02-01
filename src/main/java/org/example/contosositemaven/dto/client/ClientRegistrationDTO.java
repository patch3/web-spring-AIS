package org.example.contosositemaven.dto.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ClientRegistrationDTO extends ClientDTO{
    @Getter
    @Setter
    protected String repeatPassword;

    public ClientRegistrationDTO(Long id,
                                 String fullName,
                                 String email,
                                 boolean confirmedAccount,
                                 MultipartFile passportPhoto,
                                 String password,
                                 String repeatPassword) throws IOException {
        super(id, fullName, email, confirmedAccount, passportPhoto, password);
        this.repeatPassword = repeatPassword;
    }
    public boolean passwordIsEquals(){
        return super.password.equals(this.repeatPassword);
    }

}
