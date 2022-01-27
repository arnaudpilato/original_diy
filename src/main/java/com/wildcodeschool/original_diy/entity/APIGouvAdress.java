package com.wildcodeschool.original_diy.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class APIGouvAdress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Double x;
    private Double y;

    @OneToMany(mappedBy = "cartography")
    private List<DiyWorkshop> workshops = new ArrayList<>();

    public APIGouvAdress() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public List<DiyWorkshop> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(List<DiyWorkshop> workshops) {
        this.workshops = workshops;
    }
}
