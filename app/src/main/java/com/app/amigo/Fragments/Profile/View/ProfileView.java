package com.app.amigo.Fragments.Profile.View;

import android.app.Activity;

import com.app.amigo.Models.PersonalProfile.GetCurrentProfileData.PersonalProfileData;

public interface ProfileView {
    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity,String msg);

    void setData(PersonalProfileData data);
}
