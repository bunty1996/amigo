package com.app.amigo.Handler;

import com.app.amigo.Models.Favourites.FavouritesExample;

public interface GetFavUserHandler {
    public void onSuccess(FavouritesExample favouritesExample);
    public void onError(String message);
}
