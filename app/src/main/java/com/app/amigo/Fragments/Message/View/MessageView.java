package com.app.amigo.Fragments.Message.View;

import android.app.Activity;

import com.app.amigo.Models.Message.GetFriendList.GetFriendListDatum;

import java.util.List;

public interface MessageView {
    public void showDialog(Activity activity);

    public void hideDialog();

    public void showMessage(Activity activity, String msg);

    public void setData(List<Object> items);



  public   void setdatanewmatches(List<GetFriendListDatum> data);



}
