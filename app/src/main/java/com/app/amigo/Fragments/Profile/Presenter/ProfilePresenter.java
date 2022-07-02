package com.app.amigo.Fragments.Profile.Presenter;

import android.app.Activity;
import android.graphics.Bitmap;

import com.app.amigo.Fragments.Profile.View.ProfileView;
import com.app.amigo.Handler.PersonalProfileHandler;
import com.app.amigo.Handler.UploadProfileImageHandler;
import com.app.amigo.Models.PersonalProfile.GetCurrentProfileData.PersonalProfileExample;
import com.app.amigo.Models.Profile.UploadProfileimageExample;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

import java.io.ByteArrayOutputStream;
import java.util.Random;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfilePresenter {
    private Activity activity;
    private ProfileView profileView;
    private MultipartBody.Part imagePart;
    private RequestBody id;


    public ProfilePresenter(Activity activity, ProfileView profileView) {
        this.activity = activity;
        this.profileView = profileView;
    }


    public void uploadprofile(Bitmap photo) {
        if (photo != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 40, stream);
            byte[] photoByteArray = stream.toByteArray();
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), photoByteArray);
            id = RequestBody.create(MediaType.parse("multipart/form-data"), CSPreferences.readString(activity, Utils.USERID));
            Random random = new Random();
            imagePart = MultipartBody.Part.createFormData("file", "abc" + random.nextInt(1000000) + ".jpg", requestFile);
        }
        if (Utils.isNetworkConnected(activity)){
            profileView.showDialog(activity);
            String id  = CSPreferences.readString(activity,Utils.USERID);
            WebServices.getmInstance().uploadprofileimage(imagePart, id, new UploadProfileImageHandler() {
                @Override
                public void onSuccess(UploadProfileimageExample uploadProfileimageExample) {
                    profileView.hideDialog();
                    if(uploadProfileimageExample!=null){
                        profileView.showMessage(activity,uploadProfileimageExample.getData());
//                               CSPreferences.putString(activity,Utils.ImageBaseURL, uploadProfileimageExample.getData().get.getProfileImageName());
//                        Toast.makeText(activity, "Profile image upload sucessfully", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onError(String message) {
                    profileView.hideDialog();
                    profileView.showMessage(activity,message);

                }
            });
        }

    }


    public void personalprofileData() {
        if (Utils.isNetworkConnected(activity)) {
            profileView.showDialog(activity);
            String id = CSPreferences.readString(activity, Utils.USERID);
            WebServices.getmInstance().getpersonalprofile(id, new PersonalProfileHandler() {
                @Override
                public void onSuccess(PersonalProfileExample personalProfileExample) {
                    profileView.hideDialog();
                    if (personalProfileExample != null) {
                        if (personalProfileExample.getIsSuccess() == true) {
//                            CSPreferences.putString(activity, Utils.USERPHOTO, personalProfileExample.getData().getProfileImageName());

//                            CSPreferences.putString(activity, Utils.NAME, personalProfileExample.getData().getName());
//                            CSPreferences.putString(activity, Utils.ABOUTME, personalProfileExample.getData().getAboutMe());
                            CSPreferences.putString(activity,Utils.USERPHOTO,personalProfileExample.getData().getProfileImageName());

                            profileView.setData(personalProfileExample.getData());


                        }
                    } else {
                        profileView.showMessage(activity, personalProfileExample.getMessage());
                    }
                }

                @Override
                public void onError(String message) {

                    profileView.hideDialog();
                    profileView.showMessage(activity, message);
                }
            });
        }
    }
}
