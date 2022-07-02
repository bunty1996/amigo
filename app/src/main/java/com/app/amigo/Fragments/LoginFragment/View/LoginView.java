package com.app.amigo.Fragments.LoginFragment.View;

import android.app.Activity;

import com.app.amigo.Models.Login.LoginData;

public interface LoginView {
    public void showMessage(Activity activity, String msg);
    public void showDialog(Activity activity);
    public void hideDialog();

    public void setData(Activity activity, LoginData data);

}
