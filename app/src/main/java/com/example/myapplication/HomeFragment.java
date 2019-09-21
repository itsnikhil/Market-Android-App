package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    private ViewPager viewPager;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        this.context = view.getContext();

        viewPager = view.findViewById(R.id.viewpage);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(context);
        viewPager.setAdapter(viewPagerAdapter);
        return view;
    }
}
