package com.example.selimelik.imagelist.pojos;

import java.io.Serializable;

public class Place implements Serializable {
    private String place_id;
    private String place_name;
    private String place_address;
    private String place_lat;
    private String place_lng;
    private String place_city;
    private String place_state;
    private String place_formattedAddress;


    public Place(String place_id, String place_name, String place_address, String place_lat, String place_lng, String place_city, String place_state, String place_formattedAddress) {
        this.place_id = place_id;
        this.place_name = place_name;
        this.place_address = place_address;
        this.place_lat = place_lat;
        this.place_lng = place_lng;
        this.place_city = place_city;
        this.place_state = place_state;
        this.place_formattedAddress = place_formattedAddress;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getPlace_address() {
        return place_address;
    }

    public void setPlace_address(String place_address) {
        this.place_address = place_address;
    }

    public String getPlace_lat() {
        return place_lat;
    }

    public void setPlace_lat(String place_lat) {
        this.place_lat = place_lat;
    }

    public String getPlace_lng() {
        return place_lng;
    }

    public void setPlace_lng(String place_lng) {
        this.place_lng = place_lng;
    }

    public String getPlace_city() {
        return place_city;
    }

    public void setPlace_city(String place_city) {
        this.place_city = place_city;
    }

    public String getPlace_state() {
        return place_state;
    }

    public void setPlace_state(String place_state) {
        this.place_state = place_state;
    }

    public String getPlace_formattedAddress() {
        return place_formattedAddress;
    }

    public void setPlace_formattedAddress(String place_formattedAddress) {
        this.place_formattedAddress = place_formattedAddress;
    }
}
