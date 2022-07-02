package com.app.amigo.Fragments.Favourite.Presenter;

import android.app.Activity;

import com.app.amigo.Adapters.FavouritesRecylerAdapter;
import com.app.amigo.Fragments.Favourite.FavouriteFragment;
import com.app.amigo.Fragments.Favourite.View.FavouriteView;
import com.app.amigo.Handler.RemoveFromFavHandler;
import com.app.amigo.Models.Favourites.RemovefromFavs.RemoveFromFavExample;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

public class RemoveFromFavPresenter {
    private  FavouritesRecylerAdapter favouritesRecylerAdapter;
    private Activity activity;
    private FavouriteView favouriteView;


    public RemoveFromFavPresenter(Activity activity, FavouriteView favouriteView) {
        this.activity = activity;
        this.favouriteView = favouriteView;

    }

    public RemoveFromFavPresenter(Activity activity, FavouritesRecylerAdapter favouritesRecylerAdapter) {
        this.activity=activity;
        this.favouritesRecylerAdapter=favouritesRecylerAdapter;

    }


}
