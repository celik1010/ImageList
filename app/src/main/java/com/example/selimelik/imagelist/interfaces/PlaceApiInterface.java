package com.example.selimelik.imagelist.interfaces;

import com.example.selimelik.imagelist.pojos.place.Place;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlaceApiInterface {

    @GET("v2/venues/search?client_id=C2L4DPI1JXR2VBRCN4FXZUO5XODUXFELK50VCNHNZ52Z0EJH&client_secret=SLROW2EOR44ZFDR3Z4N4O5NLA5LQJY2BPVWOVJDBTTWT43PR&v=20190226")
    Call<Place> getPlacesRetrofit(@Query("query") String query, @Query("ll") String ll, @Query("limit") int limit);
}
