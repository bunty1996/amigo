package com.app.amigo.Activities.PersonalProfile.View;

import android.app.Activity;

import com.app.amigo.Models.PersonalProfile.GetCurrentProfileData.PersonalProfileData;

public interface PersonalProfileView {
    public void showDialog(Activity activity);

    public void hideDialog();

    public void showMessage(Activity activity, String msg);

   public void setData(PersonalProfileData data);
}
