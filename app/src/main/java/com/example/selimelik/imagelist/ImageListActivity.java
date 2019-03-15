package com.example.selimelik.imagelist;

import android.content.Intent;;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ImageListActivity extends AppCompatActivity {

    private TextView mTextMessage;
    ListView listView;
    PostClass adapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    ArrayList<String> userIdfromFB;
    ArrayList<String> placeIdfromFB;
    ArrayList<String> imagePathfromFB;
    ArrayList<String> uuidString;
    ArrayList<String> postTime;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent= new Intent(getApplicationContext(),SelectPalce.class);
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
        userIdfromFB = new ArrayList<>();
        placeIdfromFB = new ArrayList<>();
        imagePathfromFB = new ArrayList<>();
        postTime = new ArrayList<>();
        uuidString = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();

        adapter = new PostClass(placeIdfromFB,userIdfromFB,imagePathfromFB,uuidString,postTime,this);
        listView.setAdapter(adapter);

        getDataFromFirebase();


       /* Intent intent1= new Intent(getApplicationContext(),SaveCommentImage.class);
        intent1.putExtra("placeName","dename");
        startActivity(intent1);
*/
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void getDataFromFirebase(){
        DatabaseReference newReference = firebaseDatabase.getReference("POSTS/IMAGEPATHS");
        Query query = newReference.orderByChild("USER_ID").startAt("slm").endAt("slm\uf8ff").limitToFirst(10);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               // System.out.println("FBV Children :" +dataSnapshot.getChildren());
               //  System.out.println("FBV key :" +dataSnapshot.getKey());
               //   System.out.println("FBV value :" +dataSnapshot.getValue());
               //   System.out.println("FBV priority :" +dataSnapshot.getPriority());

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                  //  System.out.println("FBV DS VALUE : "+ds.getValue());

                    HashMap<String,String> hashMap = (HashMap<String, String>) ds.getValue();
                 //   hashMap.get("FBV PlaceID :" + hashMap.get("PLACE_ID"));
                 //   hashMap.get("FBV USER_ID :" + hashMap.get("USER_ID"));
                 //  hashMap.get("FBV IMAGE_PATH :" + hashMap.get("IMAGE_PATH"));

                    userIdfromFB.add(hashMap.get("USER_ID"));
                    placeIdfromFB.add(hashMap.get("PLACE_ID"));
                    imagePathfromFB.add(hashMap.get("IMAGE_PATH"));
                    postTime.add(hashMap.get("POSTTIME"));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
