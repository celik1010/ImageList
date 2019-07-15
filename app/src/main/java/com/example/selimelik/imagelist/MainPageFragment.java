package com.example.selimelik.imagelist;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.selimelik.imagelist.adapters.PostAdapter;
import com.example.selimelik.imagelist.pojos.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainPageFragment extends Fragment {
    ListView listView;
    PostAdapter adapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    ArrayList<Post> postsFromFB;

    public MainPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);

        listView = view.findViewById(R.id.listView);
        postsFromFB =new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();

        adapter = new PostAdapter(getActivity(),postsFromFB);
        listView.setAdapter(adapter);
        getDataFromFirebase();

        return view;
    }

    public void getDataFromFirebase(){
        DatabaseReference newReference = firebaseDatabase.getReference("POSTS");
        //   Query query = newReference.orderByChild("username").startAt("cel").endAt("cel\uf8ff").limitToFirst(10);
        Query query = newReference.orderByChild("timestamp").limitToFirst(10);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 System.out.println("FBV Children :" +dataSnapshot.getChildren());
                  System.out.println("FBV key :" +dataSnapshot.getKey());
                   System.out.println("FBV value :" +dataSnapshot.getValue());
                   System.out.println("FBV priority :" +dataSnapshot.getPriority());

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Post post = ds.getValue(Post.class);
                    post.setPost_id(ds.getKey());
                    postsFromFB.add(post);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
