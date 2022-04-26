package com.wildcodeschool.original_diy.request;

public class BadgeRequest {
    private String name;

    private String picturePath;

    private String description;

    private int step;

    private String condition;

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

    public String getCondition() { return condition; }

    public void setCondition(String condition) { this.condition = condition; }
}
