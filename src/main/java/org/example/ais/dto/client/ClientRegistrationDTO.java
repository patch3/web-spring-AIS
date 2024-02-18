package org.example.ais.dto.client;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public final class ClientRegistrationDTO extends ClientDTO {

    private String repeatPassword;

    public ClientRegistrationDTO() {
        super.confirmedAccount = false;
    }

    public boolean passwordIsEquals() {
        return super.password.equals(this.repeatPassword);
    }
}
