package com.example.selimelik.imagelist.pojos;

public class User {
    private String email;
    private String username;
    private String password;
    private String name;
    private int gender;
    private String website;
    private String userInfo;
    private String photoPath;
    private String registerDate;
    private long timestamp;

    public User() {

    }

    public User(String email, String username, String password, String name, int gender, String website, String userInfo, String photoPath, String registerDate, long timestamp) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.website = website;
        this.userInfo = userInfo;
        this.photoPath = photoPath;
        this.registerDate = registerDate;
        this.timestamp = timestamp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
