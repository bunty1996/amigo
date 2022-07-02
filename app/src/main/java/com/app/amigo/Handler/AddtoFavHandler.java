package com.app.amigo.Handler;


import com.app.amigo.Models.Trends.FavouriteExample;
import com.example.AddtoFavExample;

public interface AddtoFavHandler {
    public void onSuccess(AddtoFavExample addtoFavExample );
    public void onError(String message);
}
