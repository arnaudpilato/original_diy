package com.wildcodeschool.original_diy.request;

public class FooterRequest {
    private String name;

    private String socialNetworkPath;

    private String picturePath;

    private boolean visible;

    public String getName() { return name; }

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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
