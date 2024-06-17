package com.coulon.todo.app.utils.managers;

import com.coulon.todo.app.utils.crypto.CryptoUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class CredentialsManager {

    public static final CredentialsManager INSTANCE = new CredentialsManager();
    private Credentials credentials = null;
    private final File credentialsJsonFile;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private CredentialsManager() {
        String appFolderPath = System.getProperty("user.home") + "/.TodoApp";
        File appFolder = new File(appFolderPath);

        if (!appFolder.exists()) {
            appFolder.mkdirs();
        }

        String credentialsFilePath = appFolderPath + "/credentials.json";
        credentialsJsonFile = new File(credentialsFilePath);

        if (credentialsJsonFile.exists()) {
            try {
                credentials = objectMapper.readValue(credentialsJsonFile, new TypeReference<>() {});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(String email, String password) {
        this.credentials = new Credentials(CryptoUtils.encrypt(email), CryptoUtils.encrypt(password));
        try {
            objectMapper.writeValue(credentialsJsonFile, credentials);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
