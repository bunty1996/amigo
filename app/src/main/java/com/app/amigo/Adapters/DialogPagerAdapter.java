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

public class DialogPagerAdapter extends PagerAdapter {
    String [] text;
    String [] text1;
    Context context;

    public DialogPagerAdapter(Context context, String[] text, String[] text1) {

        this.context = context;
        this.text = text;
        this.text1 = text1;

    }

    @Override
    public int getCount() {
        return text.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((View) object);
    }

    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.viewpagetextlist, container, false);
        TextView tv_text = view.findViewById(R.id.tv_text);
        TextView tv_text1 = view.findViewById(R.id.tv_text1);
        tv_text1.setText(text1[position]);
        tv_text.setText(text[position]);



        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
