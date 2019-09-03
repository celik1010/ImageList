package com.example.selimelik.imagelist.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.selimelik.imagelist.R;


public class PlacesFragment extends Fragment {
TextView textView1;
    public PlacesFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_places, container, false);
             textView1 = view.findViewById(R.id.selimcelik);
        return view;
    }

    public void displaylist(String aa){
        textView1.setText(aa);
    }

}
