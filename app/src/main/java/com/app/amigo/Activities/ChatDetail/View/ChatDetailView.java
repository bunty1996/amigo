package com.app.amigo.Activities.ChatDetail.View;

import android.app.Activity;

public interface ChatDetailView {
    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity,String msg);
}
