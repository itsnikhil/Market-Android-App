package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;

public class SearchFragment extends Fragment {

    private FoodViewModel mViewModel;
    List<ItemModal> itemModalList;
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private GoogleSignInClient mGoogleSignInClient;
    private Context context;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.blank_fragment, container, false);
        this.context = view.getContext();

        recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        FoodViewModel model = ViewModelProviders.of(this).get(FoodViewModel.class);
        model.getItems().observe(this, item -> {
            itemModalList = item;
            layoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(layoutManager);

            mAdapter = new ListAdapter(itemModalList);
            recyclerView.setAdapter(mAdapter);
            ExtendedFloatingActionButton checkout = view.findViewById(R.id.fab);

            checkout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(view.getContext(), CheckoutActivity.class);
                    intent.putExtra("price", mAdapter.getTotal());
                    startActivity(intent);
                }
            });
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FoodViewModel.class);
        // TODO: Use the ViewModel
    }

}
