package com.example.selimelik.imagelist;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.selimelik.imagelist.adapters.PlaceListAdapter;
import com.example.selimelik.imagelist.interfaces.PlaceApiInterface;
import com.example.selimelik.imagelist.pojos.Place;
import com.example.selimelik.imagelist.pojos.place.PlaceResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectPlace extends AppCompatActivity {
    ArrayAdapter<String> placesAdapter;
    String enlem, boylam;
    String placesJSON;
    List<String> placeList;
    List<Place> placeListObj;
    RecyclerView recyclerView;
    Place place;
    ListView lstPlaceList;
    TextView txtLang, txtLong;
    EditText edtPlaceName_;
    LocationManager locationManager;
    LocationListener locationListener;
    Call<com.example.selimelik.imagelist.pojos.place.Place> call;
    PlaceResponse placeResponseList;

    private final String FSQ_CLIENT_ID = "C2L4DPI1JXR2VBRCN4FXZUO5XODUXFELK50VCNHNZ52Z0EJH";
    private final String FSQ_CLIENT_SECRET = "SLROW2EOR44ZFDR3Z4N4O5NLA5LQJY2BPVWOVJDBTTWT43PR";
    private final String FSQ_VERSION = "20190226";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_place1);

        /***********************************************************/
        recyclerView = findViewById(R.id.recyclerPlaceList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final PlaceApiInterface apiInterface = PlaceApiClient.getRetrofitInstance().create(PlaceApiInterface.class);

        /***********************************************************/


        //   txtLong = findViewById(R.id.txtlongitude);
        //   txtLang = findViewById(R.id.txtlatitude);
        edtPlaceName_ = findViewById(R.id.edtSearchText1);
      //  lstPlaceList = findViewById(R.id.lstPlaces);


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // System.out.println("location :" +location.getLongitude()+","+location.getLatitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            // locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            double lat = location1.getLatitude();
            double lon = location1.getLongitude();
            enlem = String.valueOf(lat);
            boylam = String.valueOf(lon);
            //  txtLang.setText(String.valueOf(lat));
            //   txtLong.setText(String.valueOf(lon));
            //  searchPlaces("", enlem, boylam);
            // getCityName(40.9964840143779, 29.509277343750004);
        }
      /*  lstPlaceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intentSelectImage = new Intent(getApplicationContext(), SaveCommentImage.class);
                intentSelectImage.putExtra("placeName", placeList.get(position));
                System.out.println("adadad : " + placeListObj);
                place = placeListObj.get(position);
                intentSelectImage.putExtra("place", placeListObj.get(position));
                startActivity(intentSelectImage);
            }
        });*/

        edtPlaceName_.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                call = apiInterface.getPlacesRetrofit(s.toString(),enlem+","+boylam,8);

                call.enqueue(new Callback<com.example.selimelik.imagelist.pojos.place.Place>() {
                    @Override
                    public void onResponse(Call<com.example.selimelik.imagelist.pojos.place.Place> call, Response<com.example.selimelik.imagelist.pojos.place.Place> response) {
                        placeResponseList = response.body().getPlaceResponse();
                        System.out.println("asasa : " + placeResponseList.getVenues().get(0).getLocation().getFormattedAddress());
                        recyclerView.setAdapter(new PlaceListAdapter(placeResponseList,R.layout.custom_place_item,getApplicationContext()));

                    }

                    @Override
                    public void onFailure(Call<com.example.selimelik.imagelist.pojos.place.Place> call, Throwable t) {

                    }
                });
                //  searchPlaces(s.toString(), enlem, boylam);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





     /*   double lat = location.getLatitude();
        double lon = location.getLongitude();
        txtLang.setText(String.valueOf(lat));
        txtLong.setText(String.valueOf(lon));
        System.out.println("asasa"+String.valueOf(lat));*/
    }

    private void searchPlaces(String pQuery, String pLati, String pLong) {

        String url = "https://api.foursquare.com/v2/venues/search?client_id=" + FSQ_CLIENT_ID + "&client_secret=" + FSQ_CLIENT_SECRET + "&v=" + FSQ_VERSION;
        url = url + "&query=" + pQuery + "&ll=" + pLati + "," + pLong + "&limit=5";

        GetPlacesData getPlacesData = new GetPlacesData();
        try {
            getPlacesData.execute(url);


        } catch (Exception e) {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0) {
            if (requestCode == 1) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                }

            }

        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public class GetPlacesData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            URL url;
            HttpsURLConnection urlConnection;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpsURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();
                while (data > 0) {
                    char character = (char) data;
                    result += character;
                    data = inputStreamReader.read();
                }
                placesJSON = result;
                return result;

            } catch (Exception e) {
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            placeList = new ArrayList<>();
            placeListObj = new ArrayList<>();
            //System.out.println("Data :" + s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                jsonObject = jsonObject.getJSONObject("response");
                System.out.println("sasa" + jsonObject.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("venues");
                JSONObject jsonLocation;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    placeList.add(jsonObject1.getString("name"));
                    jsonLocation = new JSONObject(jsonObject1.getString("location"));
                    String _city;
                    String _address;
                    try {
                        _address = jsonLocation.getString("address");
                    }catch (Exception e){
                        _address = "";
                    }
                    try {
                        _city = jsonLocation.getString("city");
                    }catch (Exception e){
                        _city = "";
                    }
                   place = new Place(jsonObject1.getString("id"),jsonObject1.getString("name"), _address, jsonLocation.getString("lat"), jsonLocation.getString("lng"),_city,jsonLocation.getString("state"),jsonLocation.getString("formattedAddress"));

                   placeListObj.add(place);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(s);
            placesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, android.R.id.text1, placeList);
            lstPlaceList.setAdapter(placesAdapter);
            lstPlaceList.refreshDrawableState();
        }
    }

    private String getCityName(double pLati, double pLong) {

        try {
            Geocoder geocoder = new Geocoder(this);
            List<Address> address = null;
            address = geocoder.getFromLocation(pLati, pLong, 1);
            String city = address.get(0).getLocality();
            city = address.get(0).getAdminArea();
            //  String country = address.get(0).getCountryName();
            return city;
        } catch (Exception e) {
            return "";
        }
    }

}
