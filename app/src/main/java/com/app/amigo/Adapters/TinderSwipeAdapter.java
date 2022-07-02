package com.app.amigo.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.amigo.Activities.PersonalProfile.PersonalProfileActivity;
import com.app.amigo.Models.Trends.TinderSwipeModel;
import com.app.amigo.R;

import java.util.ArrayList;

public class TinderSwipeAdapter extends BaseAdapter {

    // on below line we have created variables
    // for our array list and context.
    private ArrayList<TinderSwipeModel> courseData;
    private Activity activity;

    ImageView idIVCourse;

    public TinderSwipeAdapter(ArrayList<TinderSwipeModel> courseModalArrayList, Activity activity) {
        this.courseData = courseModalArrayList;
        this.activity = activity;
    }

    // on below line we have created constructor for our variables.


    @Override
    public int getCount() {
        // in get count method we are returning the size of our array list.
        return courseData.size();
    }

    @Override
    public Object getItem(int position) {
        // in get item method we are returning the item from our array list.
        return courseData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // in get item id we are returning the position.
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // in get view method we are inflating our layout on below line.
        View v = convertView;
        if (v == null) {
            // on below line we are inflating our layout.
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tinderswipeitem, parent, false);
        }

        ImageView imageView = v.findViewById(R.id.idIVCourse);
        TextView idTVCourseName = v.findViewById(R.id.idTVCourseName);
        TextView idTVCourseDescription = v.findViewById(R.id.idTVCourseDescription);
        TextView tv_logo = v.findViewById(R.id.tv_logo);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PersonalProfileActivity.class);
                activity.startActivity(intent);
            }
        });
        // on below line we are initializing our variables and setting data to our variables.
        idTVCourseName.setText(courseData.get(position).getCourseName());
        idTVCourseDescription.setText(courseData.get(position).getCourseDescription());
        imageView.setImageResource(courseData.get(position).getImgId());
        tv_logo.setText(courseData.get(position).getCourseDuration());
//        ((TextView) v.findViewById(R.id.idTVCourseDuration)).setText(courseData.get(position).getCourseDuration());
//        ((TextView) v.findViewById(R.id.idTVCourseTracks)).setText(courseData.get(position).getCourseTracks());

//

        return v;
    }


}
