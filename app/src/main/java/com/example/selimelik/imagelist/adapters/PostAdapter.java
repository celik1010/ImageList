package com.example.selimelik.imagelist.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.selimelik.imagelist.R;
import com.example.selimelik.imagelist.pojos.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends ArrayAdapter<Post> {
    private Activity context;
    private ArrayList<Post> posts;

    public PostAdapter(Activity context, ArrayList<Post> posts) {
        super(context, R.layout.custom_view, posts);
        this.context = context;
        this.posts = posts;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View customView = layoutInflater.inflate(R.layout.custom_view, null, true);

        TextView txtUserId = customView.findViewById(R.id.txtusername);
        TextView txtPostTime = customView.findViewById(R.id.txtTime);
        ImageView imageView = customView.findViewById(R.id.imageView3);


        {   //Like & Dislike Buttons
            Button btnDislike = customView.findViewById(R.id.btnDislike);
            Button btnLike = customView.findViewById(R.id.btnLike);
            float percent = (float) Math.random();

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, percent);
            btnDislike.setLayoutParams(params);
            percent = 1 - percent;
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, percent);
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


        txtUserId.setText(posts.get(position).getUsername());
        txtPostTime.setText(posts.get(position).getPostdate());
        Picasso.get().load(posts.get(position).getImage_path()).into(imageView);


        return customView;
    }
}
