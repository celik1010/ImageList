package com.example.selimelik.imagelist.pojos.place;


import com.google.gson.annotations.Expose;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceResponse {

    @SerializedName("venues")

    @Expose

    public List<Venue> venues = null;

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }
}