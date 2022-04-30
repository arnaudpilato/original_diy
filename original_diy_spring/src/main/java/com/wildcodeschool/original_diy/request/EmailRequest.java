package com.wildcodeschool.original_diy.request;

import com.wildcodeschool.original_diy.entity.DiyUser;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class EmailRequest {
    private String email;

    public EmailRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
