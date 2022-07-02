package com.app.amigo.Fragments.Favourite.View;

import android.app.Activity;

import com.app.amigo.Models.Favourites.FavouritesDatum;

import java.util.List;

public interface FavouriteView {
    public void showDialog(Activity activity);

    public void hideDialog();

    public void showMessage(Activity activity, String msg);

//    public void setRecyclerData(List<FavouritesDatum> data);
}
