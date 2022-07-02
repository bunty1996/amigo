package com.app.amigo.Fragments.Favourite;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.amigo.Adapters.FavouritesRecylerAdapter;
import com.app.amigo.Fragments.Favourite.Presenter.FavouritesPresenter;
import com.app.amigo.Fragments.Favourite.Presenter.RemoveFromFavPresenter;
import com.app.amigo.Fragments.Favourite.View.FavouriteView;
import com.app.amigo.Models.Favourites.FavouritesDatum;
import com.app.amigo.R;
import com.app.amigo.Utility;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment implements FavouriteView, SwipeRefreshLayout.OnRefreshListener {
    private Activity activity;
    private View view;
    private LinearLayout linearlayout;
    private String gender;
    private RecyclerView recyclerview;
    private FavouritesPresenter favouritePresenter;
    private List<FavouritesDatum> dataList = new ArrayList<>();
    private FavouritesRecylerAdapter favouritesRecylerAdapter;
    private RemoveFromFavPresenter removeFromFavPresenter;
    private SwipeRefreshLayout refreshlayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        changetheme();
        view = inflater.inflate(R.layout.fragment_favourite, container, false);
        refreshlayout = view.findViewById(R.id.refreshlayout);
        refreshlayout.setOnRefreshListener(this);
        activity = getActivity();
        init();
        listeners();
        customtheme();
        favouritePresenter = new FavouritesPresenter(activity, this);
        favouritePresenter.getFavoriteuser(recyclerview);

        removeFromFavPresenter = new RemoveFromFavPresenter(activity, this);


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

    @Override
    public void onRefresh() {
        favouritePresenter.getFavoriteuser(recyclerview);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshlayout.setRefreshing(false);
            }
        }, 1000);


    }

}






