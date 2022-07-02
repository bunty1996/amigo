package com.app.amigo.Adapters;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.app.amigo.Models.PersonalProfile.InterestModelClass;
import com.app.amigo.R;

import java.util.ArrayList;

public class InterestRecyclerAdapter extends RecyclerView.Adapter<InterestRecyclerAdapter.ViewHolder> {
    private Activity activity;
    private View view;
    private ArrayList<InterestModelClass>arrayList;

    public InterestRecyclerAdapter(Activity activity, ArrayList<InterestModelClass> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public InterestRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.interestslist,parent,false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_interest.setText(arrayList.get(position).getInterests());
        if (position%2==0){
//            holder.tv_interest.setBackgroundColor(activity.getResources().getColor(R.color.yellow));
            holder.tv_interest.setBackground(activity.getDrawable(R.drawable.lightcolobackground));

        }else{
            holder.tv_interest.setBackground(activity.getDrawable(R.drawable.darkcolobackground));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.tv_interest.setTextColor(activity.getColor(R.color.white));
            }


        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_interest;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_interest = itemView.findViewById(R.id.tv_interest);
        }
    }
}
