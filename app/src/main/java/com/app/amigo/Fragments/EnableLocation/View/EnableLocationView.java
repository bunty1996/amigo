package com.app.amigo.Fragments.EnableLocation.View;

import android.app.Activity;

public interface EnableLocationView {
    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity,String msg);
}
