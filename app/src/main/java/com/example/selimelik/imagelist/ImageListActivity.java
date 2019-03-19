package com.example.selimelik.imagelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.selimelik.imagelist.adapters.PostAdapter;
import com.example.selimelik.imagelist.pojos.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class ImageListActivity extends AppCompatActivity {
    private static ImageListActivity imageListActivity;
    public static final String POSTSTABLENAME = "POSTS";
    private TextView mTextMessage;
    ListView listView;
    PostAdapter adapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    ArrayList<Post> postsFromFB;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent= new Intent(getApplicationContext(),SelectPlace.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);



        listView = findViewById(R.id.listView);
        postsFromFB =new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();

        adapter = new PostAdapter(this,postsFromFB);
        listView.setAdapter(adapter);

        getDataFromFirebase();


       /* Intent intent1= new Intent(getApplicationContext(),SaveCommentImage.class);
        intent1.putExtra("placeName","dename");
        startActivity(intent1);
*/
        mTextMessage =  findViewById(R.id.message);
        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void getDataFromFirebase(){
        DatabaseReference newReference = firebaseDatabase.getReference(ImageListActivity.POSTSTABLENAME);
        Query query = newReference.orderByChild("username").startAt("slm").endAt("slm\uf8ff").limitToFirst(10);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               // System.out.println("FBV Children :" +dataSnapshot.getChildren());
               //  System.out.println("FBV key :" +dataSnapshot.getKey());
               //   System.out.println("FBV value :" +dataSnapshot.getValue());
               //   System.out.println("FBV priority :" +dataSnapshot.getPriority());

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                     Post post = ds.getValue(Post.class);
                     postsFromFB.add(post);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
public static ImageListActivity getInstance(){
        if (imageListActivity==null)
            imageListActivity = new ImageListActivity();
        return imageListActivity;
}
}
