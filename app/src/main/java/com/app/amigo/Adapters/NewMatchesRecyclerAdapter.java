package com.app.amigo.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.amigo.Activities.ChatDetail.ChatDetailActivity;
import com.app.amigo.Models.Message.GetFriendList.GetFriendListDatum;
import com.app.amigo.Models.Message.MatchesModelClass;
import com.app.amigo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewMatchesRecyclerAdapter extends RecyclerView.Adapter<NewMatchesRecyclerAdapter.ViewHolder> {
    private List<GetFriendListDatum> data;
    private Activity activity;

    public NewMatchesRecyclerAdapter(Activity activity, List<GetFriendListDatum> data) {
        this.activity = activity;
        this.data = data;

    }


    @NonNull
    @Override
    public NewMatchesRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.newmatcheslist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewMatchesRecyclerAdapter.ViewHolder holder, int position) {

        if (data.get(position).getProfileImageName().isEmpty()) {

            holder.cimg_profileimage.setImageResource(R.drawable.userimage);
        }else {
            Picasso.with(activity).load(data.get(position).getProfileImageName()).into(holder.cimg_profileimage);

        }



        holder.tv_username.setText(data.get(position).getName());
        if (data.get(position).getStatus().equalsIgnoreCase("active")){
            holder.oval.setColorFilter(Color.parseColor("#008000"));
        }else{
            holder.oval.setColorFilter(Color.parseColor("#FF0000"));
        }

        holder.cimg_profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ChatDetailActivity.class);
                intent.putExtra("id",data.get(position).getId());
                intent.putExtra("name", data.get(position).getName());
                intent.putExtra("URL", data.get(position).getProfileImageName());
                activity.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView cimg_profileimage;
        ImageView oval;
        TextView tv_username;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cimg_profileimage=itemView.findViewById(R.id.cimg_profileimage);
            oval=itemView.findViewById(R.id.oval);
            tv_username=itemView.findViewById(R.id.tv_username);


        }
    }
}
