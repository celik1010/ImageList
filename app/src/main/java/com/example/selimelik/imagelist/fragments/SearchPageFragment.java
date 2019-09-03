package com.example.selimelik.imagelist.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.selimelik.imagelist.R;
import com.example.selimelik.imagelist.adapters.SectionsPageAdapter;
import com.example.selimelik.imagelist.interfaces.SearchPageCommunicator;

public class SearchPageFragment extends Fragment {
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    EditText edtSearchText;

    public SearchPageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_page, container, false);
        mSectionsPageAdapter = new SectionsPageAdapter(getActivity().getSupportFragmentManager());
        mViewPager = view.findViewById(R.id.viewPagerContainer);
        setupViewPager(mViewPager);
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);

        edtSearchText = view.findViewById(R.id.editSearchText);
        edtSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mViewPager.getCurrentItem() == 0) {
                    fillPlaceList(edtSearchText.getText().toString());
                } else {
                    fillPeopleList(edtSearchText.getText().toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }



    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new PlacesFragment(), "Yerler");
        adapter.addFragment(new PeopleFragment(), "Kişiler");
        viewPager.setAdapter(adapter);
    }

    public void fillPlaceList(String edtText) {
        FragmentManager manager = getFragmentManager();
        PlacesFragment placesFragment = (PlacesFragment) manager.findFragmentByTag("android:switcher:" + R.id.viewPagerContainer + ":" + mViewPager.getCurrentItem());
        placesFragment.displaylist(edtText);
    }
    public void fillPeopleList(String edtText) {
     //   FragmentManager manager = getFragmentManager();
     //   PeopleFragment peopleFragment = (PeopleFragment) manager.findFragmentByTag("android:switcher:" + R.id.viewPagerContainer + ":" + mViewPager.getCurrentItem());
     //   peopleFragment.displaylist(edtText);
    }
}
