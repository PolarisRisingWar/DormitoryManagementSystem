package com.example.dormitorymanagementsystem;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.support.design.widget.TabLayout;

public class LifeFragment extends Fragment {

    public LifeFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_life, container, false);
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ViewPager viewPager = getActivity().findViewById(R.id.viewpager);
        LifePageAdapter pageAdapter=new LifePageAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);

        TabLayout tabLayout=getActivity().findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
