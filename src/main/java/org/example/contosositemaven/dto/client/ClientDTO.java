package org.example.contosositemaven.dto.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


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

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }

    public boolean isConfirmedAccount() {
        return confirmedAccount;
    }

    public MultipartFile getPassportPhoto() {
        return passportPhoto;
    }

    public String getPassword() {
        return password;
    }
    public String getPasswordHash(){
        return passwordEncoder.encode(password);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setConfirmedAccount(boolean confirmedAccount) {
        this.confirmedAccount = confirmedAccount;
    }

    public void setPassportPhoto(MultipartFile passportPhoto) {
        this.passportPhoto = passportPhoto;
    }

    public void setPassword(String passwordHash) {
        this.password = passwordHash;
    }
}
