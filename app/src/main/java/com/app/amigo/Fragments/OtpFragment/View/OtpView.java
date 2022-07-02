package com.app.amigo.Fragments.OtpFragment.View;

import android.app.Activity;

public interface OtpView {
    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity,String msg);
}
