package com.example.selimelik.imagelist;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostClass extends ArrayAdapter<String> {
    private final ArrayList<String> placeId;
    private final ArrayList<String> userId;
    private final ArrayList<String> imagePath;
    private final ArrayList<String> uuidString;
    private final ArrayList<String> postTime;
    private final Activity context;


    public PostClass(ArrayList<String> placeId, ArrayList<String> userId, ArrayList<String> imagePath, ArrayList<String> uuidString, ArrayList<String> postTime, Activity context) {
        super(context,R.layout.custom_view,imagePath);
        this.placeId = placeId;
        this.userId = userId;
        this.imagePath = imagePath;
        this.uuidString = uuidString;
        this.postTime = postTime;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View customView = layoutInflater.inflate(R.layout.custom_view,null,true);

        TextView txtUserId = customView.findViewById(R.id.txtusername);
        TextView txtPostTime = customView.findViewById(R.id.txtTime);
        ImageView imageView = customView.findViewById(R.id.imageView3);


        {   //Like & Dislike Buttons
            Button btnDislike = customView.findViewById(R.id.btnDislike);
            Button btnLike = customView.findViewById(R.id.btnLike);
            float percent = (float)Math.random();

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT,percent);
            btnDislike.setLayoutParams(params);
            percent = 1-percent;
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT,percent);
            btnLike.setLayoutParams(params);

            btnDislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        txtUserId.setText(placeId.get(position));
        txtPostTime.setText(postTime.get(position));
        Picasso.get().load(imagePath.get(position)).into(imageView);



        return customView;
    }


}
