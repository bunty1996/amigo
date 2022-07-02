package com.app.amigo.Fragments.Trends.Presenter;

import android.app.Activity;

import androidx.fragment.app.FragmentActivity;

import com.app.amigo.Fragments.Trends.TrendsFragment;
import com.app.amigo.Fragments.Trends.View.TrendsView;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TrendsPresenter {
    private Activity activity;
    private TrendsView trendsView;



    public TrendsPresenter(@Nullable FragmentActivity activity, @NotNull TrendsView trendsView) {
        this.activity = activity;
        this.trendsView = trendsView;
    }



}
