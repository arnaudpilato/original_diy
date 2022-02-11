package com.wildcodeschool.original_diy.response;

import com.wildcodeschool.original_diy.entity.DiyUser;

public class JwtResponse {
    private String token;

    private String type = "Bearer";

    private DiyUser user;

    public JwtResponse(String accessToken, DiyUser user) {
        this.token = accessToken;
        this.user = user;
    }

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
}
