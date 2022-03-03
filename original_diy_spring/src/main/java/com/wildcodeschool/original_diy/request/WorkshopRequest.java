package com.wildcodeschool.original_diy.request;

import com.wildcodeschool.original_diy.entity.DiyUser;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;


public class WorkshopRequest {
    private String title;

    private String picturePath;

    private Long streetNumber;

    private String street;

    private Long postCode;

    private String city;

    private String description;

    private Double longitude;

    private Double latitude;

    private boolean confirmation = false;

    private DiyUser diyUser;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm ", iso = DateTimeFormat.ISO.DATE)
    private Date date;

    @DateTimeFormat(pattern = "hh:mm")
    private Date hours;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getHours() {
        return hours;
    }

    public void setHours(Date hours) {
        this.hours = hours;
    }

    public WorkshopRequest() {
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

    public Long getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Long streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getPostCode() {
        return postCode;
    }

    public void setPostCode(Long postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    public DiyUser getDiyUser() {
        return diyUser;
    }

    public void setDiyUser(DiyUser diyUser) {
        this.diyUser = diyUser;
    }
}
