package com.app.amigo.Fragments.Signup.View;

import android.app.Activity;

public interface SignUpView {
    public void showMessage(Activity activity, String msg);
    public void showDialog(Activity activity);
    public void hideDialog();
}
