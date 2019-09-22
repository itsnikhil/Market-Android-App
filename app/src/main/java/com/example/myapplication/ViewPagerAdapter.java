package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3};
    private int counter;

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        counter = position;
        Log.d("temp", "reset "+counter);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_holder, null);
        ImageView imageView = view.findViewById(R.id.imageButton);
        Picasso.get()
                .load(images[position])
                .resize(600, 400)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(imageView);

        ViewPager vp = (ViewPager) container;
        ImageView nextImgBtn = view.findViewById(R.id.nextImage);
        nextImgBtn.setOnClickListener(v -> {
            Log.d("temp", "before "+counter);
            vp.setCurrentItem((position+1)%images.length);
            Log.d("temp", "after "+counter);
        });
        ImageView prevImgBtn = view.findViewById(R.id.prevImage);
        prevImgBtn.setOnClickListener(v -> {
            Log.d("temp", "before "+counter);
            if(position == 0) {
                counter = images.length - 1;
                Log.d("temp", "after "+counter);
            }
            else {
                counter = position-1;
                Log.d("temp", "after "+counter);
            }
            vp.setCurrentItem(counter);
        });
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
