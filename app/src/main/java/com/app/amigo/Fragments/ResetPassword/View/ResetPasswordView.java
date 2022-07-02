package com.app.amigo.Fragments.ResetPassword.View;

import android.app.Activity;
import android.widget.ImageView;

public interface ResetPasswordView {
    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity,String msg);

}
