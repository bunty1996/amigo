package com.app.amigo.Fragments.ForgotPassword.View;

import android.app.Activity;

public interface ForgotPasswordView {
    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity,String msg);
}
