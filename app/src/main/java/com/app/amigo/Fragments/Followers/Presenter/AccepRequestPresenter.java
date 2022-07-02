package com.app.amigo.Fragments.Followers.Presenter;

import android.app.Activity;

import com.app.amigo.Fragments.Followers.FollowersFragment;
import com.app.amigo.Fragments.Followers.View.FollowersView;
import com.app.amigo.Handler.AcceptRequestHandler;
import com.app.amigo.Models.Followers.AcceptRequest.AcceptRequestExample;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

public class AccepRequestPresenter {
    private Activity activity;
    private FollowersView followersView;

    public AccepRequestPresenter(Activity activity, FollowersView followersView) {
        this.activity = activity;
        this.followersView = followersView;

    }


    public void acceptrequest(){
        if(Utils.isNetworkConnected(activity)){
            followersView.showDialog(activity);
            String id="hi";
            String token = CSPreferences.readString(activity, Utils.TOKEN);
            WebServices.getmInstance().acceptrequest(token, id, new AcceptRequestHandler() {
                @Override
                public void onSuccess(AcceptRequestExample acceptRequestExample) {
                    followersView.hideDialog();
                    if(acceptRequestExample!=null){
                        if(acceptRequestExample.getIsSuccess()==true){
                            followersView.showMessage(activity,acceptRequestExample.getMessage());
                        }
                    }else{
                        followersView.showMessage(activity,acceptRequestExample.getMessage());
                    }
                }

                @Override
                public void onError(String message) {
                    followersView.hideDialog();
                    followersView.showMessage(activity, message);

                }
            });
        }
    }
}
