package com.app.amigo.Fragments.Favourite.Presenter;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.amigo.Adapters.FavouritesRecylerAdapter;
import com.app.amigo.Fragments.Favourite.View.FavouriteView;
import com.app.amigo.Handler.GetFavUserHandler;
import com.app.amigo.Handler.RemoveFromFavHandler;
import com.app.amigo.Models.Favourites.FavouritesExample;
import com.app.amigo.Models.Favourites.RemovefromFavs.RemoveFromFavExample;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

public class FavouritesPresenter {
    private static Activity activity;
    private static FavouriteView favouriteView;
    private String userid;

    public FavouritesPresenter(Activity activity, FavouriteView favouriteView) {
        this.activity = activity;
        this.favouriteView = favouriteView;

    }

    public void getFavoriteuser(RecyclerView recyclerview) {
        if (Utils.isNetworkConnected(activity)) {
//            favouriteView.showDialog(activity);
            userid = CSPreferences.readString(activity, Utils.USERID);

            WebServices.getmInstance().getfavuser(userid, new GetFavUserHandler() {
                @Override
                public void onSuccess(FavouritesExample favouritesExample) {
//                    favouriteView.hideDialog();
                    if (favouritesExample != null) {
                        if (favouritesExample.getIsSuccess() == true) {
//                            favouriteView.showMessage(activity, favouritesExample.getMessage());
//                            favouriteView.setRecyclerData(favouritesExample.getData());

                            FavouritesRecylerAdapter favouritesRecylerAdapter = new FavouritesRecylerAdapter(activity,
                                    favouritesExample.getData());
                            recyclerview.setHasFixedSize(true);
                            recyclerview.setLayoutManager(new LinearLayoutManager(activity));
                            recyclerview.setAdapter(favouritesRecylerAdapter);
                        }
                    } else {
                        favouriteView.showMessage(activity, favouritesExample.getMessage());
                    }

                }

                @Override
                public void onError(String message) {
//                    favouriteView.hideDialog();
                    favouriteView.showMessage(activity, message);

                }
            });
        }
    }

    public static void removeFromFav(String anotherid) {
        if (Utils.isNetworkConnected(activity)) {
            favouriteView.showDialog(activity);
            String id = CSPreferences.readString(activity, Utils.USERID);
//            String anotheruserid = CSPreferences.readString(activity, Utils.PROFILEIDANOTHERUSER);
            WebServices.getmInstance().removeFromFav(id, anotherid, new RemoveFromFavHandler() {
                @Override
                public void onSuccess(RemoveFromFavExample removeFromFavExample) {
                    favouriteView.hideDialog();
                    if (removeFromFavExample != null) {
                        if (removeFromFavExample.getIsSuccess() == true) {
                            favouriteView.showMessage(activity, removeFromFavExample.getMessage());

                        }


                    } else {
                        favouriteView.showMessage(activity, removeFromFavExample.getMessage());
                    }

                }

                @Override
                public void onError(String message) {
                    favouriteView.hideDialog();
                    favouriteView.showMessage(activity, message);

                }
            });
        }
    }
}
