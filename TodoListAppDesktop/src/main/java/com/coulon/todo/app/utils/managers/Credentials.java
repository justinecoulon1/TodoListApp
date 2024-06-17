package com.coulon.todo.app.utils.managers;

import com.coulon.todo.app.utils.crypto.CryptoUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Credentials {

    private String encryptedEmail;
    private String encryptedPassword;

    public Credentials() {
    }

    public Credentials(String encryptedEmail, String encryptedPassword) {
        this.encryptedEmail = encryptedEmail;
        this.encryptedPassword = encryptedPassword;
    }

    @JsonIgnore
    public String getEmail() {
        return CryptoUtils.decrypt(encryptedEmail);
    }

    @JsonIgnore
    public String getPassword() {
        return CryptoUtils.decrypt(encryptedPassword);
    }

    public String getEncryptedEmail() {
        return encryptedEmail;
    }

    public void setEncryptedEmail(String encryptedEmail) {
        this.encryptedEmail = encryptedEmail;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

}
