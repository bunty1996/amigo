package com.app.amigo.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.amigo.Models.PersonalProfile.GetCurrentProfileData.PersonalProfileExample;
import com.app.amigo.R;
import com.squareup.picasso.Picasso;

public class AditionalGridAdapter extends BaseAdapter {

    private final PersonalProfileExample personalProfileExample;
    private Activity activity;
    public static final int PICK_IMAGE = 1;
    private String loginValue = "";
    private SharedPreferences pref;
    private int SELECT_PICTURE = 200;
    private OnViewClick onViewClick;

    public AditionalGridAdapter(Activity activity, PersonalProfileExample personalProfileExample) {

        this.activity = activity;
        this.personalProfileExample = personalProfileExample;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(activity).inflate(R.layout.item_addition_gridlayout, parent, false);
            ImageView img_add = listitemView.findViewById(R.id.img_add);
            if (personalProfileExample.getData().getGallery()==null){

            }else{
                Picasso.with(activity).load(personalProfileExample.getData().getGallery().get(position).getImage()).placeholder(R.drawable.userdummy).into(img_add);

            }
            img_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onViewClick.onItemClick(position,img_add);
                }
            });
        }
        pref = activity.getSharedPreferences("pref", Context.MODE_PRIVATE);
        return listitemView;

    }

    public void ViewOnClickLitener(OnViewClick onViewClick){

        this.onViewClick = onViewClick;

    }

    public interface OnViewClick{

        void onItemClick(int position, ImageView img_add);
    }


}
