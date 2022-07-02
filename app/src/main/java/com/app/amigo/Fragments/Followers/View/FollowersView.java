package com.app.amigo.Fragments.Followers.View;

import android.app.Activity;

import com.app.amigo.Models.Followers.GetUserRequest.GetUserRequestDatum;

import java.util.List;

public interface FollowersView {
    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity,String msg);


    public void setData(List<GetUserRequestDatum> data);

    public void removeRequest(String reqBy);
}
