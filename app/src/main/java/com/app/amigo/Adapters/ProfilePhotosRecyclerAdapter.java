package com.app.amigo.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.amigo.Models.PersonalProfile.GetCurrentProfileData.PersonalProfileData;
import com.app.amigo.Models.PersonalProfile.GetCurrentProfileData.PersonalProfileGallery;
import com.app.amigo.Models.PersonalProfile.ProfilePhotosModelClass;
import com.app.amigo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProfilePhotosRecyclerAdapter extends RecyclerView.Adapter<ProfilePhotosRecyclerAdapter.ViewHolder> {
    private final List<PersonalProfileGallery> personalProfileData;
    private Activity activity;
    private View view;
    private ArrayList<ProfilePhotosModelClass> arrayList;


//    public ProfilePhotosRecyclerAdapter(Activity activity, ArrayList<ProfilePhotosModelClass> arrayList1) {
//        this.activity = activity;
//        this.arrayList = arrayList1;
//    }

    public ProfilePhotosRecyclerAdapter(Activity activity, List<PersonalProfileGallery> data) {
        this.activity = activity;
        this.personalProfileData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(activity).inflate(R.layout.profilephotoslist, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (personalProfileData.get(position).getImage().isEmpty()) {
            holder.img_profileimage.setImageResource(R.drawable.userdummy);
        } else {
            Picasso.with(activity).load(personalProfileData.get(position).getImage()).
                    placeholder(R.drawable.userdummy).into(holder.img_profileimage);
        }
    }

    @Override
    public int getItemCount() {
        return personalProfileData.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_profileimage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_profileimage = itemView.findViewById(R.id.img_profileimage);
        }
    }
}
