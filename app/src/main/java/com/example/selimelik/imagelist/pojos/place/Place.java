package com.example.selimelik.imagelist.pojos.place;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Place {

    @SerializedName("response")
    @Expose
    public PlaceResponse placeResponse;

    public PlaceResponse getPlaceResponse() {
        return placeResponse;
    }

    public void setPlaceResponse(PlaceResponse response) {
        this.placeResponse = response;
    }


}
