package org.example.ais.dto.client;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public final class ClientRegistrationDTO extends ClientDTO{
    private String repeatPassword;

    /*
        public ClientRegistrationDTO(
                String fullName,
                String email,
                boolean confirmedAccount,
                MultipartFile passportPhoto,
                String password,
                String repeatPassword
        ) throws IOException {
            super(fullName, email, confirmedAccount, passportPhoto, password);
            this.repeatPassword = repeatPassword;
        }
        */
    public boolean passwordIsEquals(){
        return super.password.equals(this.repeatPassword);
    }
}
