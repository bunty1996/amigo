package com.app.amigo.Handler;

import com.app.amigo.Models.Favourites.RemovefromFavs.RemoveFromFavExample;
import com.app.amigo.Models.Signup.RegisterExample;

public interface RemoveFromFavHandler {
    public void onSuccess(RemoveFromFavExample removeFromFavExample);
    public void onError(String message);
}
