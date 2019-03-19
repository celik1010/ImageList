package com.example.selimelik.imagelist.pojos;

public class Post {
    private String post_id;
    private String username;
    private String place_id;
    private String image_path;
    private String postdate;
    private long timestamp;
    private String catagory;

    public Post() {
    }

    public Post(String post_id, String username, String place_id, String image_path, String postdate, long timestamp, String catagory) {
        this.post_id = post_id;
        this.username = username;
        this.place_id = place_id;
        this.image_path = image_path;
        this.postdate = postdate;
        this.timestamp = timestamp;
        this.catagory = catagory;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

}
