package com.app.amigo.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.amigo.Activities.PersonalProfile.PersonalProfileActivity;
import com.app.amigo.Fragments.Favourite.FavouriteFragment;
import com.app.amigo.Fragments.Favourite.Presenter.FavouritesPresenter;
import com.app.amigo.Fragments.Favourite.Presenter.RemoveFromFavPresenter;
import com.app.amigo.Models.Favourites.FavouritesDatum;
import com.app.amigo.R;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavouritesRecylerAdapter extends RecyclerView.Adapter<FavouritesRecylerAdapter.ViewHolder> {


    private final List<FavouritesDatum> dataList;
    //    private ArrayList<FavouritesModelClass>dataList;
    private Activity activity;

    public FavouritesRecylerAdapter(Activity activity, List<FavouritesDatum> dataList) {
        this.dataList = dataList;
        this.activity = activity;

    }

//    public FavouritesRecylerAdapter(Activity activity, ArrayList<FavouritesModelClass> arrayList) {
//        this.activity = activity;
//        this.dataList = arrayList;
//
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.favouriteslist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if (dataList.get(position).getProfileImageName().isEmpty()) {

            holder.img_userimage.setImageResource(R.drawable.userimage);
        }else {
            Picasso.with(activity).load(dataList.get(position).getProfileImageName()).into(holder.img_userimage);

        }
        holder.tv_username.setText(dataList.get(position).getName());
        holder.img_userimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PersonalProfileActivity.class);
                intent.putExtra("anotherId",dataList.get(position).getId());
                intent.putExtra("Setting","fav");
                activity.startActivity(intent);
                activity.finish();
            }
        });
        holder.img_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(activity);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.removetofavlayout);

                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();

                wlp.gravity = Gravity.CENTER;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
                window.setAttributes(wlp);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                Button btn_remove = (Button) dialog.findViewById(R.id.btn_remove);
                String gender = CSPreferences.readString(activity, Utils.GENDERSELECT);

                btn_remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        CSPreferences.putString(activity, );
                        FavouritesPresenter.removeFromFav(dataList.get(position).getId());
                        Utils.changeFragment2(activity, new FavouriteFragment());
                        dialog.dismiss();

                    }
                });

                Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_userimage;
        TextView tv_username;
        LinearLayout linearlayout;
        ImageView img_remove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_userimage = itemView.findViewById(R.id.img_userimage);
            tv_username = itemView.findViewById(R.id.tv_username);
            linearlayout = itemView.findViewById(R.id.linearlayout);
            img_remove = itemView.findViewById(R.id.img_remove);
        }
    }
}
