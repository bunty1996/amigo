package com.app.amigo.Fragments.Additionalinformation.presenter;

import android.app.Activity;
import android.content.Intent;
import android.widget.GridView;
import android.widget.ImageView;

import com.app.amigo.Activities.Home.HomeActivity;
import com.app.amigo.Adapters.AditionalGridAdapter;
import com.app.amigo.Fragments.Additionalinformation.view.AditionalInformationView;
import com.app.amigo.Fragments.EnableLocation.View.EnableLocationView;
import com.app.amigo.Handler.AdditionalInformationHandler;
import com.app.amigo.Handler.PersonalProfileHandler;
import com.app.amigo.Models.Additionalinformation.AditionalInformationExample;
import com.app.amigo.Models.PersonalProfile.GetCurrentProfileData.PersonalProfileExample;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

import java.util.ArrayList;

public class AditionalInformationPresenter {
    private AditionalInformationView informationView;
    private EnableLocationView enableLocationView;
    private Activity activity;
    private GridView gridView;
    private String et_aboutyou;
    private String et_currentjob;
    private String et_education;
    private String et_city;
    private String et_height;
    private String et_weight;
    private String et_favsports;
    private String et_lookingfor;
    private String et_lasteducation;
    private String name;
    private String email;
    private String phonenum;
    private String dob;
    private String lookingfor;


    public AditionalInformationPresenter(AditionalInformationView informationView, Activity activity, GridView gridView) {

        this.informationView = informationView;
        this.activity = activity;
        this.gridView = gridView;

//        updateAdapter(personalProfileExample);

    }

    public AditionalInformationPresenter(EnableLocationView enableLocationView, Activity activity) {
        this.activity = activity;
        this.enableLocationView = enableLocationView;

    }


    public void userlist(String name, String email, String phonenum, String dob, String lookingfor,
                         String et_aboutyou, String et_currentjob, ArrayList<String> list, String et_education, String et_city, String et_height, String et_weight,
                         String et_favsports, String et_lookingfor, String et_lasteducation, String selSex) {
        this.name = name;
        this.email = email;
        this.phonenum = phonenum;
        this.dob = dob;
        this.lookingfor = lookingfor;
        this.et_aboutyou = et_aboutyou;
        this.et_currentjob = et_currentjob;
        this.et_education = et_education;
        this.et_city = et_city;
        this.et_height = et_height;
        this.et_weight = et_weight;
        this.et_favsports = et_favsports;
        this.et_lookingfor = et_lookingfor;
        this.et_lasteducation = et_lasteducation;

        if (Utils.isNetworkConnected(activity)) {

            enableLocationView.showDialog(activity);
            String token = CSPreferences.readString(activity, Utils.TOKEN);
            String userid = CSPreferences.readString(activity, Utils.USERID);
            String emailid = CSPreferences.readString(activity, Utils.USEREMAIL);
//            String sex = "male";

            String status = "active";

            WebServices.getmInstance().updatelist(token, userid, name, emailid, et_aboutyou, "", phonenum, dob, et_education, et_aboutyou,
                    et_city, et_height, et_weight, et_favsports, et_lasteducation, lookingfor, "", et_currentjob, selSex, status, String.valueOf(list), "" +
                            "", "", new AdditionalInformationHandler() {
                        //                    @Override
                        public void onSuccess(AditionalInformationExample additionalInformationExample) {

                            enableLocationView.hideDialog();
                            if (additionalInformationExample != null) {
                                if (additionalInformationExample.getIsSuccess() == true) {
//                                informationView.showMessage(activity, additionalInformationExample.get());

//                                Utils.changeFragment(activity,new EnableLocationFragment());
                                    CSPreferences.putString(activity, Utils.USERLOGIN, "true");
                                    Intent i = new Intent(activity, HomeActivity.class);
                                    i.putExtra("Setting", "");

                                    activity.startActivity(i);
                                    ((Activity) activity).overridePendingTransition(0, 0);

                                }
//                            informationView.showMessage(activity, additionalInformationExample.getMessage());

                            } else {
//                            informationView.showMessage(activity, additionalInformationExample.getMessage());
                            }

                        }

                        @Override
                        public void onError(String message) {
                            enableLocationView.hideDialog();
                            enableLocationView.showMessage(activity, message);

                        }
                    });


        }


    }

    public void personalprofileData() {
        if (Utils.isNetworkConnected(activity)) {
//            settingView.showDialog(activity);
            String id = CSPreferences.readString(activity, Utils.USERID);
            WebServices.getmInstance().getpersonalprofile(id, new PersonalProfileHandler() {
                @Override
                public void onSuccess(PersonalProfileExample personalProfileExample) {
                    informationView.hideDialog();
                    if (personalProfileExample != null) {
                        if (personalProfileExample.getIsSuccess() == true) {
                            updateAdapter(personalProfileExample);
                            CSPreferences.putString(activity, Utils.USERPHOTO, personalProfileExample.getData().getProfileImageName());
                            informationView.setData(personalProfileExample.getData());
//
//                            if (!(personalProfileExample.getData().getGallery()==null)){
//                                informationView.checkimagesMethod();
//                            }else if (personalProfileExample.getData().getGallery().size()==6){
//                                informationView.checkGallerySizeMethod();
//                            }


//                            CSPreferences.putString(activity, Utils.USERPHOTO, personalProfileExample.getData().getProfileImageName());

//                            CSPreferences.putString(activity, Utils.NAME, personalProfileExample.getData().getName());
//                            CSPreferences.putString(activity, Utils.ABOUTME, personalProfileExample.getData().getAboutMe());


                        }
                    } else {
                        informationView.showMessage(activity, personalProfileExample.getMessage());
                    }
                }

                @Override
                public void onError(String message) {
                    informationView.hideDialog();
                    informationView.showMessage(activity, message);
                }
            });
        }
    }


    private void updateAdapter(PersonalProfileExample personalProfileExample) {
        AditionalGridAdapter aditionalGridAdapter = new AditionalGridAdapter(activity, personalProfileExample);
        gridView.setAdapter(aditionalGridAdapter);
        aditionalGridAdapter.ViewOnClickLitener(new AditionalGridAdapter.OnViewClick() {
            @Override
            public void onItemClick(int position, ImageView img_add) {
                informationView.selectImage(img_add);
            }
        });


    }


}
