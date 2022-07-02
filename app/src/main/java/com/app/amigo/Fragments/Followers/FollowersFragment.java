package com.app.amigo.Fragments.Followers;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.amigo.Adapters.FollowersListRecyclerAdapter;
import com.app.amigo.Fragments.Followers.Presenter.AccepRequestPresenter;
import com.app.amigo.Fragments.Followers.Presenter.FollowersPresenter;
import com.app.amigo.Fragments.Followers.Presenter.RejectRequestPresenter;
import com.app.amigo.Fragments.Followers.View.FollowersView;
import com.app.amigo.Models.Followers.GetUserRequest.GetUserRequestDatum;
import com.app.amigo.R;
import com.app.amigo.Utility;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class FollowersFragment extends Fragment implements FollowersView, SwipeRefreshLayout.OnRefreshListener {
    private Activity activity;
    private View view;
    private String gender;
    private LinearLayout linearlayout;
    private RecyclerView recyclerview;
    private FollowersListRecyclerAdapter followersListRecyclerAdapter;
    //    String [] text = {"Buy more top picks","Buy more top picks"};
//    String [] text1 = {"here is the method","here is the method"};
    private FollowersPresenter followersPresenter;
    private RejectRequestPresenter rejectRequestPresenter;
    private AccepRequestPresenter accepRequestPresenter;
    private SwipeRefreshLayout refreshlayout;

    private List<GetUserRequestDatum> data = new ArrayList<>();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        changetheme();
        view = inflater.inflate(R.layout.fragment_followers, container, false);
        refreshlayout = view.findViewById(R.id.refreshlayout);
        refreshlayout.setOnRefreshListener(this);

        activity = getActivity();
        init();
        listeners();
        customtheme();
        followersPresenter = new FollowersPresenter(activity, this,recyclerview);
        if(savedInstanceState==null){
            followersPresenter.getuserrequest();
        }

        setData(data);



        rejectRequestPresenter = new RejectRequestPresenter(activity,this);
        accepRequestPresenter = new AccepRequestPresenter(activity,this);
        return view;
    }


    private void init() {
        linearlayout = view.findViewById(R.id.linearlayout);
        recyclerview = view.findViewById(R.id.recyclerview);

    }

    private void listeners() {
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
            linearlayout.setBackgroundColor(getResources().getColor(R.color.maleblueTheme));
//            SelectGenderActivity.indicatorSeekBar.setBackgroundColor(getResources().getColor(R.color.maleblueTheme));
            Utility.onActivityCreateSetTheme(getActivity());
        } else {
            activity.setTheme(R.style.Femaletheme);
            linearlayout.setBackgroundColor(getResources().getColor(R.color.femalepinkTheme));
        }

    }

    @Override
    public void showDialog(Activity activity) {
        Utils.showDialogMethod(activity, activity.getFragmentManager());

    }

    @Override
    public void hideDialog() {
        Utils.hideDialog();

    }

    @Override
    public void showMessage(Activity activity, String msg) {

        Utils.showMessage(activity, msg);
    }



    public void setData(List<GetUserRequestDatum> data) {
        this.data = data;
        followersListRecyclerAdapter = new FollowersListRecyclerAdapter(activity, data, this);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(activity));
        recyclerview.setAdapter(followersListRecyclerAdapter);


    }

    @Override
    public void removeRequest(String reqBy) {

    }


    @Override
    public void onRefresh() {
        followersPresenter.getuserrequest();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshlayout.setRefreshing(false);
            }
        }, 1000);



    }
}