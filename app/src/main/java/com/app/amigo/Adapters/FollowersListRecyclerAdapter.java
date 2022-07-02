package com.app.amigo.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.amigo.Activities.PersonalProfile.PersonalProfileActivity;
import com.app.amigo.Fragments.Favourite.FavouriteFragment;
import com.app.amigo.Fragments.Favourite.Presenter.FavouritesPresenter;
import com.app.amigo.Fragments.Followers.FollowersFragment;
import com.app.amigo.Fragments.Followers.Presenter.FollowersPresenter;
import com.app.amigo.Fragments.Followers.View.FollowersView;
import com.app.amigo.Models.Followers.GetUserRequest.GetUserRequestDatum;
import com.app.amigo.R;
import com.app.amigo.Utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FollowersListRecyclerAdapter extends RecyclerView.Adapter<FollowersListRecyclerAdapter.ViewHolder> {

    private final FollowersView followersView;
    private List<GetUserRequestDatum> data = new ArrayList<>();
    //    private final String[] text;
//    private final String[] text1;
    private Activity activity;

    public FollowersListRecyclerAdapter(Activity activity, List<GetUserRequestDatum> data, FollowersView followersView) {
        this.activity = activity;
        this.data = data;
        this.followersView = followersView;
    }

    @NonNull
    @Override
    public FollowersListRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.followersitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowersListRecyclerAdapter.ViewHolder holder, int position) {

        if (data.get(position).getReqBy().getPicUrl().isEmpty()) {
          holder.img_userimage.setImageResource(R.drawable.userdummy);
        } else{
            Picasso.with(activity).load(data.get(position).getReqBy().getPicUrl()).into(holder.img_userimage);
        }


        holder.tv_username.setText(data.get(position).getReqBy().getName());
        holder.img_userimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PersonalProfileActivity.class);
                intent.putExtra("anotherId",data.get(position).getReqBy().getReqBy());
                intent.putExtra("Setting","req");
                activity.startActivity(intent);
                activity.finish();
            }
        });

        holder.img_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FollowersPresenter.acceptrequest(activity,data.get(position).getReqId());
                Utils.changeFragment2(activity, new FollowersFragment());

            }
        });

        holder.img_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FollowersPresenter.rejectrequestMethod(activity, data.get(position).getReqId());
                Utils.changeFragment2(activity, new FollowersFragment());

//                FavouritesPresenter.removeFromFav(dataList.get(position).getId());
//                Utils.changeFragment2(activity, new FavouriteFragment());
//               Toast.makeText(activity, "dhhhxs", Toast.LENGTH_SHORT).show();
//
//               final Dialog dialog = new Dialog(activity);
//               dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//               dialog.setCancelable(false);
//               dialog.setContentView(R.layout.removetofavlayout);
//
//               Window window = dialog.getWindow();
//               WindowManager.LayoutParams wlp = window.getAttributes();
//
//               wlp.gravity = Gravity.CENTER;
//               wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
//               window.setAttributes(wlp);
//               dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
//               dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//
//
//               Button btn_remove = (Button) dialog.findViewById(R.id.btn_remove);
//               String gender = CSPreferences.readString(activity, Utils.GENDERSELECT);
//
//               btn_remove.setOnClickListener(new View.OnClickListener() {
//                   @Override
//                   public void onClick(View v) {
////                        CSPreferences.putString(activity, );
////                       FavouritesPresenter.removeFromFav(data.get(position).getReqBy().getReqBy());
//
//                       dialog.dismiss();
//
//                   }
//               });
//
//               Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
//               btn_cancel.setOnClickListener(new View.OnClickListener() {
//                   @Override
//                   public void onClick(View v) {
//                       dialog.dismiss();
//                   }
//               });
//
//               dialog.show();
//
            }
//

        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_userimage;
        TextView tv_username;
        LinearLayout linearlayout;
        ImageView img_accept;
        ImageView img_reject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_userimage = itemView.findViewById(R.id.img_userimage);
            tv_username = itemView.findViewById(R.id.tv_username);
            linearlayout = itemView.findViewById(R.id.linearlayout);
            img_accept = itemView.findViewById(R.id.img_accept);
            img_reject = itemView.findViewById(R.id.img_reject);

        }
    }
}
