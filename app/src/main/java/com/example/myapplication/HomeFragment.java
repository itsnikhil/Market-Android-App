package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

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
