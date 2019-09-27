package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class HomeFragment extends Fragment {

    private ViewPager viewPager;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        this.context = view.getContext();

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this.context);

        viewPager = view.findViewById(R.id.viewpage);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(context);
        viewPager.setAdapter(viewPagerAdapter);

        TextView welcomeTxt = view.findViewById(R.id.welcomeUser);
        if (acct != null) {
            String personName = acct.getDisplayName();
            welcomeTxt.setText("Hello, " + personName + "!");
        } else {
            welcomeTxt.setText("You are not authorized!");
        }

        Button showBtn = view.findViewById(R.id.show);
        showBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this.context, SearchItemActivity.class);
            startActivity(intent);
        });
        Button showAR = view.findViewById(R.id.arscene);
        showAR.setOnClickListener(v -> {
            Intent intent = new Intent(this.context, ARItem.class);
            startActivity(intent);
        });

        return view;
    }
}
