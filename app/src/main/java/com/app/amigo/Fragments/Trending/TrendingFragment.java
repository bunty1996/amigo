package com.app.amigo.Fragments.Trending;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.amigo.Activities.SelectGender.SelectGenderActivity;
import com.app.amigo.Adapters.TinderSwipeAdapter;
import com.app.amigo.Models.Trends.TinderSwipeModel;
import com.app.amigo.R;
import com.app.amigo.Utility;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.daprlabs.cardstack.SwipeDeck;
import com.daprlabs.cardstack.SwipeFrameLayout;

import java.util.ArrayList;


public class TrendingFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Activity activity;
    private SwipeDeck cardStack;
    private SwipeFrameLayout swipeFrameLayout;
    private ArrayList<TinderSwipeModel> courseModalArrayList;
    private LinearLayout select;
    private LinearLayout lin_exit;
    private LinearLayout lin_retry;
    private LinearLayout lin_star;
    private LinearLayout lin_fav;
    private LinearLayout lin_trends;
    private ImageView img_exit;
    private ImageView img_retry;
    private ImageView img_star;
    private ImageView img_fav;
    private ImageView img_trends;
    private Boolean flag = false;
    private String gender;
    private LinearLayout linearlayout;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        changetheme();

        view = inflater.inflate(R.layout.fragment_trending, container, false);
        activity = getActivity();
        init();
        listeners();
        customtheme();
        swipeFrameLayout = view.findViewById(R.id.swipes);


        courseModalArrayList = new ArrayList<>();

        courseModalArrayList.add(new TinderSwipeModel("Rohini", "30 days", "20 Tracks", "10 miles away", R.drawable.men));
        courseModalArrayList.add(new TinderSwipeModel("Rohini", "30 days", "20 Tracks", "10 miles away", R.drawable.men));
        courseModalArrayList.add(new TinderSwipeModel("Rohini", "30 days", "20 Tracks", "10 miles away", R.drawable.men));
        courseModalArrayList.add(new TinderSwipeModel("Rohini", "30 days", "20 Tracks", "10 miles away", R.drawable.men));
        final TinderSwipeAdapter adapter = new TinderSwipeAdapter(courseModalArrayList, activity);
        cardStack.setAdapter(adapter);
        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {

            }

            @Override
            public void cardSwipedRight(int position) {

            }

            @Override
            public void cardsDepleted() {

            }

            @Override
            public void cardActionDown() {
                Toast.makeText(activity, "down", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void cardActionUp() {
//                CardStackLayoutManager.setDirections(Direction.HORIZONTAL)
                Toast.makeText(activity, "up", Toast.LENGTH_SHORT).show();

            }
        });


        return view;
    }


    private void init() {
        cardStack = view.findViewById(R.id.swipe_deck);
        select = view.findViewById(R.id.linearlay);
        lin_exit = view.findViewById(R.id.lin_exit);
        img_exit = view.findViewById(R.id.img_exit);
        lin_retry = view.findViewById(R.id.lin_retry);
        img_retry = view.findViewById(R.id.img_retry);
        lin_star = view.findViewById(R.id.lin_star);
        img_star = view.findViewById(R.id.img_star);
        lin_trends = view.findViewById(R.id.lin_trends);
        img_trends = view.findViewById(R.id.img_trends);
        lin_fav = view.findViewById(R.id.lin_fav);
        img_fav = view.findViewById(R.id.img_fav);
        linearlayout = view.findViewById(R.id.linearlayout);

    }

    private void listeners() {
        lin_exit.setOnClickListener(this);
        lin_retry.setOnClickListener(this);
        lin_star.setOnClickListener(this);
        lin_trends.setOnClickListener(this);
        lin_fav.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == lin_exit) {
            lin_exit.setBackgroundResource(R.drawable.oval_dark);
            lin_retry.setBackgroundResource(R.drawable.oval_white);
            lin_trends.setBackgroundResource(R.drawable.oval_white);
            lin_fav.setBackgroundResource(R.drawable.oval_white);
            lin_star.setBackgroundResource(R.drawable.oval_white);
            img_exit.setColorFilter(ContextCompat.getColor(activity, R.color.white));
            img_fav.setColorFilter(ContextCompat.getColor(activity, R.color.fav));
            img_retry.setColorFilter(ContextCompat.getColor(activity, R.color.yellow));
            img_star.setColorFilter(ContextCompat.getColor(activity, R.color.lightblue));
            img_trends.setColorFilter(ContextCompat.getColor(activity, R.color.trends));
            cardStack.swipeTopCardLeft(200);


        } else if (v == lin_retry) {
            lin_retry.setBackgroundResource(R.drawable.oval_dark);
            lin_exit.setBackgroundResource(R.drawable.oval_white);
            lin_trends.setBackgroundResource(R.drawable.oval_white);
            lin_fav.setBackgroundResource(R.drawable.oval_white);
            lin_star.setBackgroundResource(R.drawable.oval_white);
            img_fav.setColorFilter(ContextCompat.getColor(activity, R.color.fav));
            img_retry.setColorFilter(ContextCompat.getColor(activity, R.color.yellow));
            img_star.setColorFilter(ContextCompat.getColor(activity, R.color.lightblue));
            img_trends.setColorFilter(ContextCompat.getColor(activity, R.color.trends));
            img_retry.setColorFilter(ContextCompat.getColor(activity, R.color.white));
            img_exit.setColorFilter(ContextCompat.getColor(activity, R.color.black));

        } else if (v == lin_star) {
            lin_star.setBackgroundResource(R.drawable.oval_dark);
            lin_exit.setBackgroundResource(R.drawable.oval_white);
            lin_retry.setBackgroundResource(R.drawable.oval_white);
            lin_trends.setBackgroundResource(R.drawable.oval_white);
            lin_fav.setBackgroundResource(R.drawable.oval_white);
            img_fav.setColorFilter(ContextCompat.getColor(activity, R.color.fav));
            img_retry.setColorFilter(ContextCompat.getColor(activity, R.color.yellow));
            img_star.setColorFilter(ContextCompat.getColor(activity, R.color.white));
            img_retry.setColorFilter(ContextCompat.getColor(activity, R.color.yellow));
            img_trends.setColorFilter(ContextCompat.getColor(activity, R.color.trends));
            img_exit.setColorFilter(ContextCompat.getColor(activity, R.color.black));
        } else if (v == lin_trends) {
            lin_trends.setBackgroundResource(R.drawable.oval_dark);
            lin_exit.setBackgroundResource(R.drawable.oval_white);
            lin_fav.setBackgroundResource(R.drawable.oval_white);
            lin_star.setBackgroundResource(R.drawable.oval_white);
            img_fav.setColorFilter(ContextCompat.getColor(activity, R.color.fav));
            img_trends.setColorFilter(ContextCompat.getColor(activity, R.color.white));
            img_retry.setColorFilter(ContextCompat.getColor(activity, R.color.yellow));
            img_star.setColorFilter(ContextCompat.getColor(activity, R.color.lightblue));
            img_exit.setColorFilter(ContextCompat.getColor(activity, R.color.black));

        } else if (v == lin_fav) {
            lin_fav.setBackgroundResource(R.drawable.oval_dark);
            lin_exit.setBackgroundResource(R.drawable.oval_white);
            lin_trends.setBackgroundResource(R.drawable.oval_white);
            lin_star.setBackgroundResource(R.drawable.oval_white);
            img_trends.setColorFilter(ContextCompat.getColor(activity, R.color.trends));
            img_fav.setColorFilter(ContextCompat.getColor(activity, R.color.white));
            img_retry.setColorFilter(ContextCompat.getColor(activity, R.color.yellow));
            img_star.setColorFilter(ContextCompat.getColor(activity, R.color.lightblue));
            img_exit.setColorFilter(ContextCompat.getColor(activity, R.color.black));

        }


    }

    private void changetheme() {
        gender = CSPreferences.readString(getActivity(), Utils.GENDERSELECT);
        if (gender.equalsIgnoreCase("Male")) {
            getActivity().setTheme(R.style.Maletheme);
            Utility.onActivityCreateSetTheme(getActivity());
        } else {
            getActivity().setTheme(R.style.Femaletheme);

        }
    }

    private void customtheme() {
        if (gender.equalsIgnoreCase("Male")) {
            activity.setTheme(R.style.Maletheme);
            linearlayout.setBackground(getResources().getDrawable(R.drawable.menbackimage));
            SelectGenderActivity.indicatorSeekBar.setBackgroundColor(getResources().getColor(R.color.maleblueTheme));
            Utility.onActivityCreateSetTheme(getActivity());
        } else {
            activity.setTheme(R.style.Femaletheme);
            linearlayout.setBackgroundColor(getResources().getColor(R.color.femalepinkTheme));
        }

    }
}