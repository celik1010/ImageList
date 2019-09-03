package com.example.selimelik.imagelist.pojos.place;

import java.util.List;

import com.google.gson.annotations.Expose;

import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("lat")

    @Expose

    public Float lat;

    @SerializedName("lng")

    @Expose

    public Float lng;

    @SerializedName("distance")

    @Expose

    public Integer distance;

    @SerializedName("cc")

    @Expose

    public String cc;

    @SerializedName("state")

    @Expose

    public String state;

    @SerializedName("country")

    @Expose

    public String country;

    @SerializedName("formattedAddress")

    @Expose

    public List<String> formattedAddress = null;

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(List<String> formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
}
