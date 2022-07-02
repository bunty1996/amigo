package com.app.amigo.Fragments.ForgotResetPassword.View;

import android.app.Activity;

public interface ForgotResetPasswordView {
    public void showMessage(Activity activity, String msg);
    public void showDialog(Activity activity);
    public void hideDialog();
}
