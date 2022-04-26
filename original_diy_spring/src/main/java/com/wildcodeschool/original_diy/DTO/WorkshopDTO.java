package com.wildcodeschool.original_diy.DTO;

import com.wildcodeschool.original_diy.entity.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkshopDTO {

    private Long id;
    private List<DiyUser> reservationUser;

    private Date date;

    private String description;

    private String title;
    private String picturePath;

    private Long limitedPlaces;

    private DiySubCategory subCategory;

    private DiyCategory diyCategory;


    public WorkshopDTO(Long id, List<DiyUser> reservationUser, Date date, String description, String title,
                       String picturePath, Long limitedPlaces, DiySubCategory subCategory, DiyCategory diyCategory) {
        this.id = id;
        this.reservationUser = reservationUser;
        this.date = date;
        this.description = description;
        this.title = title;
        this.picturePath = picturePath;
        this.limitedPlaces = limitedPlaces;
        this.subCategory = subCategory;
        this.diyCategory = diyCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DiyUser> getReservationUser() {
        return reservationUser;
    }

    public void setReservationUser(List<DiyUser> reservationUser) {
        this.reservationUser = reservationUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Long getLimitedPlaces() {
        return limitedPlaces;
    }

    public void setLimitedPlaces(Long limitedPlaces) {
        this.limitedPlaces = limitedPlaces;
    }

    public DiySubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(DiySubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public DiyCategory getDiyCategory() {
        return diyCategory;
    }

    public void setDiyCategory(DiyCategory diyCategory) {
        this.diyCategory = diyCategory;
    }
}
