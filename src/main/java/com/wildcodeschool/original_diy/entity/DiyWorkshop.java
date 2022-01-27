package com.wildcodeschool.original_diy.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DiyWorkshop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String picture;
    private Long streetNumber;
    private String street;
    private Long postCode;
    private String city;
    private String description;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = true)
    private APIGouvAdress cartography;


    @ManyToMany(mappedBy = "workshops")
    private List<DiyUser> users = new ArrayList<>();

    public DiyWorkshop() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public APIGouvAdress getCartography() {
        return cartography;
    }

    public void setCartography(APIGouvAdress cartography) {
        this.cartography = cartography;
    }

    public List<DiyUser> getUsers() {
        return users;
    }

    public void setUsers(List<DiyUser> users) {
        this.users = users;
    }
}