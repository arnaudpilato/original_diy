package com.wildcodeschool.original_diy.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DiyWorkshopUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private DiyUser currentUser;
    @ManyToOne
    private DiyWorkshop workshop;

    public DiyWorkshopUser(DiyUser currentUser, DiyWorkshop workshop) {
        this.currentUser = currentUser;
        this.workshop = workshop;
    }

    public DiyWorkshopUser() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiyUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(DiyUser currentUser) {
        this.currentUser = currentUser;
    }

    public DiyWorkshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(DiyWorkshop workshop) {
        this.workshop = workshop;
    }
}
