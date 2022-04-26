package com.wildcodeschool.original_diy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "workshops")
public class DiyWorkshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<DiyUser> reservationUser;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm ")
    private Date date;

    @Column(length = 65535, columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "diyWorkshop", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("id DESC")
    @JsonManagedReference("workshopComment")
    private final List<DiyComment> comments = new ArrayList<DiyComment>();

    private String title;

    private String picturePath;

    private Long streetNumber;

    private String street;

    private Long postCode;

    private String city;

    private Double longitude;

    private Double latitude;

    @ManyToOne(fetch = FetchType.LAZY)
    private DiyUser diyUser;

    private boolean confirmation = false;

    private int limitedPlaces;

    @ManyToOne
    private DiySubCategory subCategorycategorie;

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

    public DiyUser getDiyUser() {
        return diyUser;
    }

    public void setDiyUser(DiyUser diyUser) {
        this.diyUser = diyUser;
    }

    public List<DiyComment> getComments() {
        return comments;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<DiyUser> getReservationUser() {
        return reservationUser;
    }

    public void setReservationUser(List<DiyUser> reservationUser) {
        this.reservationUser = reservationUser;
    }

    public int getLimitedPlaces() {
        return limitedPlaces;
    }

    public void setLimitedPlaces(int limitedPlaces) {
        this.limitedPlaces = limitedPlaces;
    }

    public DiySubCategory getSubCategorycategorie() {
        return subCategorycategorie;
    }

    public void setSubCategorycategorie(DiySubCategory subCategorycategorie) {
        this.subCategorycategorie = subCategorycategorie;
    }
}