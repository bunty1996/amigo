package com.app.amigo.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.app.amigo.R;
import com.bumptech.glide.Glide;

public class PprofileViewPager_Adapter extends PagerAdapter {
    int[] images;
    Context context;

    public PprofileViewPager_Adapter(Activity activity, int[] images) {
        this.context = activity;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((View) object);
    }


    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewpagerlist, container, false);
        ImageView imageView = view.findViewById(R.id.img_userimage);
        Glide.with(context).load(images[position]).into(imageView);


        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
