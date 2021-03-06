package com.wildcodeschool.original_diy.request;

import com.wildcodeschool.original_diy.entity.DiyUser;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class CommentRequest {
    private String comment;

    @DateTimeFormat(pattern = "dd/MM/yyyy h:mm ")
    private Date createdAt;

    private Long diyWorkshopId;

    private DiyUser diyUser;

    public CommentRequest() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getDiyWorkshopId() {
        return diyWorkshopId;
    }

    public void setDiyWorkshopId(Long diyWorkshopId) {
        this.diyWorkshopId = diyWorkshopId;
    }

    public DiyUser getDiyUser() {
        return diyUser;
    }

    public void setDiyUser(DiyUser diyUser) {
        this.diyUser = diyUser;
    }
}
