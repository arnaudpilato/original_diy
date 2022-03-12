package com.wildcodeschool.original_diy.entity;

import javax.persistence.*;

@Entity
@Table(name = "footers")
public class DiyFooter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String socialNetworkPath;

    private String picturePath;

    private boolean visible;

    public DiyFooter() { }

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

    public boolean isVisible() { return visible; }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
