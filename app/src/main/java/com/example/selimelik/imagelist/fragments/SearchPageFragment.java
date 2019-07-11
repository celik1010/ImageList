package com.example.selimelik.imagelist.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.selimelik.imagelist.R;
import com.example.selimelik.imagelist.adapters.SectionsPageAdapter;

public class SearchPageFragment extends Fragment {
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;


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

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new PlacesFragment(), "Yerler");
        adapter.addFragment(new PeopleFragment(), "Kişiler");
        viewPager.setAdapter(adapter);
    }
}
