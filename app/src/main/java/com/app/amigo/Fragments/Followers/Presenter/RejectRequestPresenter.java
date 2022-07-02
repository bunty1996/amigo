package com.app.amigo.Fragments.Followers.Presenter;

import android.app.Activity;

import com.app.amigo.Fragments.Followers.FollowersFragment;
import com.app.amigo.Fragments.Followers.View.FollowersView;
import com.app.amigo.Handler.RejectRequestHandler;
import com.app.amigo.Models.Followers.RejectRequest.RejectRequestExample;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

public class RejectRequestPresenter {
    private Activity activity;
    private FollowersView followersView;


    public RejectRequestPresenter(Activity activity, FollowersView followersView) {
        this.activity = activity;
        this.followersView = followersView;

    }

    public void rejectrequestMethod() {
        if (Utils.isNetworkConnected(activity)) {
            followersView.showDialog(activity);
            String token = CSPreferences.readString(activity, Utils.TOKEN);
            String id = CSPreferences.readString(activity, Utils.USERID);
            WebServices.getmInstance().rejectrequest(token, id, new RejectRequestHandler() {
                @Override
                public void onSuccess(RejectRequestExample rejectRequestExample) {
                    if (rejectRequestExample != null) {
                        if (rejectRequestExample.getIsSuccess() == true) {
                            followersView.showMessage(activity, rejectRequestExample.getMessage());
                        }
                    } else {
                        followersView.showMessage(activity, rejectRequestExample.getMessage());
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
