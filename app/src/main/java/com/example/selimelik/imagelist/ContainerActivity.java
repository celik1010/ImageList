package com.example.selimelik.imagelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.selimelik.imagelist.fragments.PlacesFragment;
import com.example.selimelik.imagelist.fragments.SearchPageFragment;
import com.example.selimelik.imagelist.interfaces.SearchPageCommunicator;

public class ContainerActivity extends AppCompatActivity {
    private static ContainerActivity containerActivity;
    public static final String POSTSTABLENAME = "POSTS";
    public static final String LIKESTABLENAME = "LIKES";
    public static final String DISLIKESTABLENAME = "DISLIKES";
    private TextView mTextMessage;
    Fragment fragment;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment(new MainPageFragment());
                    return true;
                case R.id.navigation_search:
                    replaceFragment(new SearchPageFragment());
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent = new Intent(getApplicationContext(), SelectPlace.class);
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

        replaceFragment(new MainPageFragment());


        /* Intent intent1= new Intent(getApplicationContext(),SaveCommentImage.class);
        intent1.putExtra("placeName","dename");
        startActivity(intent1);
       */
        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public static ContainerActivity getInstance() {
        if (containerActivity == null)
            containerActivity = new ContainerActivity();
        return containerActivity;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainPage, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
