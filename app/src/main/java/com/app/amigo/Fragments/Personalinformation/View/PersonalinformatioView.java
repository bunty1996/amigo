package com.app.amigo.Fragments.Personalinformation.View;

import android.app.Activity;

import com.app.amigo.Models.PersonalProfile.GetCurrentProfileData.PersonalProfileData;

public interface PersonalinformatioView {
    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity,String msg);

    public void setData(PersonalProfileData data);
}
