package com.example.selimelik.imagelist.adapters;

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

import com.example.selimelik.imagelist.ImageListActivity;
import com.example.selimelik.imagelist.R;
import com.example.selimelik.imagelist.pojos.Post;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.UUID;

public class PostAdapter extends ArrayAdapter<Post> {
    private Activity context;
    private ArrayList<Post> posts;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;
    UUID uuid = null;
    Button btnDislike;
    Button btnLike;
    int sumDislike = 0;
    int sumLike = 0;

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

        firebaseDatabase = FirebaseDatabase.getInstance();


        final String currPostId = posts.get(position).getPost_id();
        final String currUserId = posts.get(position).getUsername();

        {   //Like & Dislike Buttons
             btnDislike = customView.findViewById(R.id.btnDislike);
             btnLike = customView.findViewById(R.id.btnLike);

            calculateLikeDislikeRates(currPostId);
            btnDislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateDislikeStatus(currPostId,currUserId);
                }
            });
            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateLikeStatus(currPostId,currUserId);
                }
            });
        }


        txtUserId.setText(posts.get(position).getPost_id());

        txtPostTime.setText(posts.get(position).getPostdate());
        Picasso.get().load(posts.get(position).getImage_path()).into(imageView);


        return customView;
    }

    private void updateDislikeStatus(String currPostId, String currUserId) {
        uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        myRef = firebaseDatabase.getReference();
        myRef.child(ImageListActivity.DISLIKESTABLENAME).child(uuidString).child("postId").setValue(currPostId);
        myRef.child(ImageListActivity.DISLIKESTABLENAME).child(uuidString).child("userId").setValue(currUserId);
    }
    private void updateLikeStatus(String currPostId, String currUserId) {
        uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        myRef = firebaseDatabase.getReference();
        myRef.child(ImageListActivity.LIKESTABLENAME).child(uuidString).child("postId").setValue(currPostId);
        myRef.child(ImageListActivity.LIKESTABLENAME).child(uuidString).child("userId").setValue(currUserId);
    }

    public void calculateLikeDislikeRates(String postId){

        myRef = FirebaseDatabase.getInstance().getReference("LIKES").child(postId);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sumDislike = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                  sumLike +=1;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myRef = FirebaseDatabase.getInstance().getReference("DISLIKES").child(postId);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sumDislike = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    sumDislike +=1;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        float percent;
        if ((sumLike+sumDislike) != 0) {
            percent = (1 * sumDislike) / (sumLike + sumDislike);
        }else{
            percent = (float) Math.random();
        }

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, percent);
        btnDislike.setLayoutParams(params);
        percent = 1 - percent;
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, percent);
        btnLike.setLayoutParams(params);
    }
}
