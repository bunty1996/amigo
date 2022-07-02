package com.app.amigo.Fragments.Trends.Presenter;

import android.app.Activity;

import androidx.fragment.app.FragmentActivity;

import com.app.amigo.Fragments.Trends.View.TrendsView;
import com.app.amigo.Handler.CreateRequestHandler;
import com.app.amigo.Models.Trends.CreateRequest.CreateRequestExample;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CreateRequestPresenter {
    private Activity activity;
    private TrendsView trendsView;


    public CreateRequestPresenter(@Nullable FragmentActivity activity, @NotNull TrendsView trendsView) {
        this.activity = activity;
        this.trendsView = trendsView;
    }

    public void crearteRequestMethod(String anotheruserid) {
        if (Utils.isNetworkConnected(activity)) {
            String token = CSPreferences.readString(activity, Utils.TOKEN);
            String id = CSPreferences.readString(activity, Utils.USERID);
//            String anotheruserid = CSPreferences.readString(activity, Utils.ANOTHERUSERID);


            WebServices.getmInstance().createRequest(token, anotheruserid, id, new CreateRequestHandler() {
                @Override
                public void onSuccess(CreateRequestExample createRequestExample) {
                    trendsView.hideDialog();
                    if (createRequestExample != null) {
                        if (createRequestExample.getIsSuccess() == true) {
//                            trendsView.showMessage(activity, createRequestExample.getMessage());

                        }
                    } else {
                        trendsView.showMessage(activity, createRequestExample.getMessage());
                    }
                }

                @Override
                public void onError(String message) {
                    trendsView.hideDialog();
                    trendsView.showMessage(activity, message);

                }
            });


        }
    }
}
