package com.wildcodeschool.original_diy.request;


public class PasswordRequest {
    private String password;

    private String token;

    public PasswordRequest() {
    }

    public String getEmail() {
        return password;
    }

    public void setEmail(String email) {
        this.password = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
