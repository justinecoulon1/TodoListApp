package com.coulon.todo.app.common.dto;

public class SessionDto {

    private String token;

    public SessionDto() {
    }

    public SessionDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
