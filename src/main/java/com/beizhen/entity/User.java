package com.beizhen.entity;

public class User {

    private String imgData;
    private String imgName;
    private String user_info;

    public User() {
    }

    public User(String imgData, String imgName, String user_info) {
        this.imgData = imgData;
        this.imgName = imgName;
        this.user_info = user_info;
    }

    public String getImgData() {
        return imgData;
    }

    public void setImgData(String imgData) {
        this.imgData = imgData;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getUser_info() {
        return user_info;
    }

    public void setUser_info(String user_info) {
        this.user_info = user_info;
    }

}
