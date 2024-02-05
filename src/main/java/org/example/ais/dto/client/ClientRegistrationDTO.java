package org.example.ais.dto.client;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public final class ClientRegistrationDTO extends ClientDTO{

    public ClientRegistrationDTO() {
        super.confirmedAccount = false;
    }

    private String repeatPassword;

    public boolean passwordIsEquals(){
        return super.password.equals(this.repeatPassword);
    }
}
