package com.app.amigo.Fragments.Personalinformation.Presenter;

import android.app.Activity;

import com.app.amigo.Fragments.Personalinformation.PersonalinformationFragment;
import com.app.amigo.Fragments.Personalinformation.View.PersonalinformatioView;
import com.app.amigo.Handler.PersonalProfileHandler;
import com.app.amigo.Models.PersonalProfile.GetCurrentProfileData.PersonalProfileExample;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

public class PersonalinformatioPresenter {
    private Activity activity;
    private PersonalinformatioView personalinformatioView;

    public PersonalinformatioPresenter(Activity activity, PersonalinformatioView personalinformatioView) {
        this.activity = activity;
        this.personalinformatioView = personalinformatioView;
    }

    public void personalprofileData() {
        if (Utils.isNetworkConnected(activity)) {
//            settingView.showDialog(activity);
            String id = CSPreferences.readString(activity, Utils.USERID);
            WebServices.getmInstance().getpersonalprofile(id, new PersonalProfileHandler() {
                @Override
                public void onSuccess(PersonalProfileExample personalProfileExample) {
                    personalinformatioView.hideDialog();
                    if (personalProfileExample != null) {
                        if (personalProfileExample.getIsSuccess() == true) {
//                            CSPreferences.putString(activity, Utils.USERPHOTO, personalProfileExample.getData().getProfileImageName());

//                            CSPreferences.putString(activity, Utils.NAME, personalProfileExample.getData().getName());
//                            CSPreferences.putString(activity, Utils.ABOUTME, personalProfileExample.getData().getAboutMe());
                            CSPreferences.putString(activity, Utils.USERPHOTO, personalProfileExample.getData().getProfileImageName());

                            personalinformatioView.setData(personalProfileExample.getData());


                        }
                    } else {
                        personalinformatioView.showMessage(activity, personalProfileExample.getMessage());
                    }
                }

                @Override
                public void onError(String message) {

                    personalinformatioView.hideDialog();
                    personalinformatioView.showMessage(activity, message);
                }
            });
        }
    }


}
