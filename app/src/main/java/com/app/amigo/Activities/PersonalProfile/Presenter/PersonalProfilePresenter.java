package com.app.amigo.Activities.PersonalProfile.Presenter;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.amigo.Activities.PersonalProfile.View.PersonalProfileView;
import com.app.amigo.Adapters.ProfilePhotosRecyclerAdapter;
import com.app.amigo.Handler.PersonalProfileHandler;
import com.app.amigo.Models.PersonalProfile.GetCurrentProfileData.PersonalProfileExample;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

public class PersonalProfilePresenter {
    private final RecyclerView h_recyclerview;
    private Activity activity;
    private PersonalProfileView personalProfileView;
    private ProfilePhotosRecyclerAdapter profilePhotosRecyclerAdapter;

    public PersonalProfilePresenter(Activity activity, PersonalProfileView personalProfileView, RecyclerView h_recyclerview) {
        this.activity = activity;
        this.personalProfileView = personalProfileView;
        this.h_recyclerview = h_recyclerview;

    }

    public void personalprofileData(String anotherId) {
        if (Utils.isNetworkConnected(activity)) {
            personalProfileView.showDialog(activity);

//            String id = CSPreferences.readString(activity, Utils.ANOTHERUSERID);
            WebServices.getmInstance().getpersonalprofile(anotherId, new PersonalProfileHandler() {
                @Override
                public void onSuccess(PersonalProfileExample personalProfileExample) {
                    personalProfileView.hideDialog();
                    if (personalProfileExample != null) {
                        if (personalProfileExample.getIsSuccess() == true) {
                            CSPreferences.putString(activity, Utils.USERPHOTO, personalProfileExample.getData().getProfileImageName());

//                            CSPreferences.putString(activity, Utils.NAME, personalProfileExample.getData().getName());
//                            CSPreferences.putString(activity, Utils.ABOUTME, personalProfileExample.getData().getAboutMe());
                            personalProfileView.setData(personalProfileExample.getData());
                            if (personalProfileExample.getData().getGallery()!=null){
                                profilePhotosRecyclerAdapter = new ProfilePhotosRecyclerAdapter(activity, personalProfileExample.getData().getGallery());
                                h_recyclerview.setHasFixedSize(true);
                                h_recyclerview.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
                                h_recyclerview.setAdapter(profilePhotosRecyclerAdapter);

                            }

                        }
                    } else {
                        personalProfileView.showMessage(activity, personalProfileExample.getMessage());
                    }
                }

                @Override
                public void onError(String message) {

                    personalProfileView.hideDialog();
                    personalProfileView.showMessage(activity, message);
                }
            });
        }
    }
}
