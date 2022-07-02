package com.app.amigo.Fragments.Additionalinformation.view;

import android.app.Activity;
import android.widget.ImageView;

import com.app.amigo.Models.PersonalProfile.GetCurrentProfileData.PersonalProfileData;

public interface AditionalInformationView {

    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity,String msg);

    public void selectImage(ImageView img_add);

    void setData(PersonalProfileData data);
}
