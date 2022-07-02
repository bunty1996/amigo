package com.app.amigo.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.app.amigo.R;

import java.util.ArrayList;
import java.util.List;

public class SelectGenterOrientationAdapter extends RecyclerView.Adapter<SelectGenterOrientationAdapter.ViewHolder> {
    private final ArrayList<String> list;
    private Activity activity;
    private int selectedPosition = 0;


    // RecyclerView recyclerView;
    public SelectGenterOrientationAdapter(Activity activity, ArrayList<String> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.selectgender_item, parent, false);
        SelectGenterOrientationAdapter.ViewHolder viewHolder = new SelectGenterOrientationAdapter.ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SelectGenterOrientationAdapter.ViewHolder holder, int position) {

        holder.name.setText(list.get(position));

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition = position;
                notifyDataSetChanged();
            }
        });
        if (selectedPosition == position) {
            holder.name.setBackgroundColor(activity.getResources().getColor(R.color.select_pinkcolor));
        } else {
            holder.name.setBackgroundColor(activity.getResources().getColor(R.color.transparent));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);

        }
    }

}