package com.wildcodeschool.original_diy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "badges")
public class DiyBadge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String picturePath;

    private String description;

    private int step;

    @ManyToMany(mappedBy = "badges", cascade =
            {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.PERSIST
            })
    @JsonIgnore
    private Set<DiyUser> users = new HashSet<>();

    //TODO Voir le système de suppression des badges quand ils sont attribués
    /*@PreRemove
    public void removeUsersFromBadge() {
        this.setUsers(new HashSet<>());
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public Set<DiyUser> getUsers() {
        return users;
    }

    public void setUsers(Set<DiyUser> users) {
        this.users = users;
    }
}
