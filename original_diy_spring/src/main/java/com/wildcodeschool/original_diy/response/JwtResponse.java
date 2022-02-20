package com.wildcodeschool.original_diy.response;

import com.wildcodeschool.original_diy.entity.DiyUser;

import java.util.List;

public class JwtResponse {
    private Long id;

    private String token;

    private String type = "Bearer";

    private DiyUser user;

    private List<String> roles;

    public JwtResponse(String accessToken, DiyUser user, Long id, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.user = user;
        this.roles = roles;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public DiyUser getUser() { return user; }

    public void setUser(DiyUser user) { this.user = user; }

    public List<String> getRoles() { return roles; }
}
