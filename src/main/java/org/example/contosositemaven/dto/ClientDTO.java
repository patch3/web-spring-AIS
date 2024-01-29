package org.example.contosositemaven.dto;

public class ClientDTO {
    private Long id;
    private String fullName;
    private String email;
    private boolean confirmedAccount;
    private byte[] passportPhoto;
    private String passwordHash;

    public ClientDTO(Long id,
                     String fullName,
                     String email,
                     boolean confirmedAccount,
                     byte[] passportPhoto,
                     String passwordHash) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.confirmedAccount = confirmedAccount;
        this.passportPhoto = passportPhoto;
        this.passwordHash = passwordHash;
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

    public byte[] getPassportPhoto() {
        return passportPhoto;
    }

    public String getPasswordHash() {
        return passwordHash;
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

    public void setPassportPhoto(byte[] passportPhoto) {
        this.passportPhoto = passportPhoto;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
