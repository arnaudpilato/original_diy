package com.wildcodeschool.original_diy.entity;

import javax.persistence.*;

@Entity
@Table(name = "footer")
public class DiyFooter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String socialNetworkPath;

    private String picturePath;

    public DiyFooter() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSocialNetworkPath() {
        return socialNetworkPath;
    }

    public void setSocialNetworkPath(String socialNetworkPath) {
        this.socialNetworkPath = socialNetworkPath;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
