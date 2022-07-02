package com.app.amigo.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.amigo.Activities.ChatDetail.ChatDetailActivity;
import com.app.amigo.Models.Message.GetOldChatList.GetOldChatUsersListDatum;
import com.app.amigo.Models.Message.MessagesModelClass;
import com.app.amigo.R;
import com.app.amigo.Utility;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessagesRecylerAdapter extends RecyclerView.Adapter<MessagesRecylerAdapter.ViewHolder> {
    private final List<GetOldChatUsersListDatum> data;
    private Activity activity;
    private ArrayList<MessagesModelClass> arrayList;
    String username;
    private String gender;
    private TextView tv_messagecount;


    public MessagesRecylerAdapter(Activity activity, List<GetOldChatUsersListDatum> data) {
        this.activity = activity;
        this.data = data;

    }

    @NonNull
    @Override
    public MessagesRecylerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        changetheme();
        View view = LayoutInflater.from(activity).inflate(R.layout.messageslist, parent, false);
        customtheme();
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MessagesRecylerAdapter.ViewHolder holder, int position) {
//        holder.img_ciuserprofile.setImageResource(arrayList.get(position).getImg_ciuserprofile());
//        holder.tv_usersname.setText(arrayList.get(position).getTv_usersname());
        holder.tv_usersname.setText(data.get(position).getReceiver());
        holder.tv_messages.setText(data.get(position).getLastMsg());
//        holder.tv_messages.setText(arrayList.get(position).getTv_messages());
//        holder.tv_messagecount.setText(arrayList.get(position).getTv_messagecount());
        if (data.get(position).getProfileImageName().isEmpty()) {
            holder.img_ciuserprofile.setImageResource(R.drawable.userdummy);
        } else {
            Picasso.with(activity).load(data.get(position).getProfileImageName()).into(holder.img_ciuserprofile);
        }
//        holder.img_ciuserprofile.setImageResource(data.get(position).getProfileImageName().i);
        holder.linear_userdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ChatDetailActivity.class);
//                intent.putExtra("id", data.get(position).getConvertedId());
                intent.putExtra("name", data.get(position).getReceiver());
                intent.putExtra("URL", data.get(position).getProfileImageName());
                activity.startActivity(intent);
            }
        });
        gender = CSPreferences.readString(activity, Utils.GENDERSELECT);
        if (gender.equalsIgnoreCase("Male")) {
            holder.tv_messagecount.setBackground(activity.getResources().getDrawable(R.drawable.oval_male));
        } else {
            holder.tv_messagecount.setBackground(activity.getResources().getDrawable(R.drawable.oval_dark));

        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img_ciuserprofile;
        TextView tv_usersname;
        TextView tv_messages;
        TextView tv_messagecount;
        LinearLayout linear_userdetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_ciuserprofile = itemView.findViewById(R.id.img_ciuserprofile);
            tv_usersname = itemView.findViewById(R.id.tv_usersname);
            tv_messages = itemView.findViewById(R.id.tv_messages);
            tv_messagecount = itemView.findViewById(R.id.tv_messagecount);
            linear_userdetail = itemView.findViewById(R.id.linear_userdetail);

        }
    }


    private void changetheme() {
        gender = CSPreferences.readString(activity, Utils.GENDERSELECT);
        if (gender.equalsIgnoreCase("Male")) {
            activity.setTheme(R.style.Maletheme);
            Utility.onActivityCreateSetTheme(activity);
        } else {
            activity.setTheme(R.style.Femaletheme);

        }
    }

    private void customtheme() {
        if (gender.equalsIgnoreCase("Male")) {
            activity.setTheme(R.style.Maletheme);


            Utility.onActivityCreateSetTheme(activity);
        } else {
            activity.setTheme(R.style.Femaletheme);

        }

    }
}
