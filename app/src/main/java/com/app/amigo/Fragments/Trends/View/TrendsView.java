package com.app.amigo.Fragments.Trends.View;

import android.app.Activity;

import com.app.amigo.Models.Trends.Userlist.TrendsDatum;

import java.util.List;

public interface TrendsView {
    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity,String msg);

   public void setData(List<TrendsDatum> data);

    void setposition(int position);
}
