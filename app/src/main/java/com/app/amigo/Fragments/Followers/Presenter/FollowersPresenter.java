package com.app.amigo.Fragments.Followers.Presenter;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.amigo.Adapters.FollowersListRecyclerAdapter;
import com.app.amigo.Fragments.Followers.View.FollowersView;
import com.app.amigo.Handler.AcceptRequestHandler;
import com.app.amigo.Handler.GetUserRequestHandler;
import com.app.amigo.Handler.RejectRequestHandler;
import com.app.amigo.Models.Followers.AcceptRequest.AcceptRequestExample;
import com.app.amigo.Models.Followers.GetUserRequest.GetUserRequestExample;
import com.app.amigo.Models.Followers.RejectRequest.RejectRequestExample;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

public class FollowersPresenter {
    private  RecyclerView recyclerview;
    private Activity activity;
    private  static FollowersView followersView;
    private FollowersListRecyclerAdapter followersListRecyclerAdapter;

    public FollowersPresenter(Activity activity, FollowersView followersView, RecyclerView recyclerview) {
        this.activity = activity;
        this.followersView = followersView;
        this.recyclerview = recyclerview;

    }

    public void getuserrequest() {
        if (Utils.isNetworkConnected(activity)) {
            followersView.showDialog(activity);
            String token = CSPreferences.readString(activity, Utils.TOKEN);
            String id = CSPreferences.readString(activity, Utils.USERID);
            WebServices.getmInstance().getuserrequest(token, id, new GetUserRequestHandler() {
                @Override
                public void onSuccess(GetUserRequestExample getUserRequestExample) {
                    followersView.hideDialog();
                    if (getUserRequestExample != null) {
                        if (getUserRequestExample.getIsSuccess() == true) {
//                            followersView.showMessage(activity, getUserRequestExample.getMessage());
//                            followersView.setData(getUserRequestExample.getData());

                            followersListRecyclerAdapter = new FollowersListRecyclerAdapter(activity, getUserRequestExample.getData(),followersView);
                            recyclerview.setHasFixedSize(true);
                            recyclerview.setLayoutManager(new LinearLayoutManager(activity));
                            recyclerview.setAdapter(followersListRecyclerAdapter);
                        }
                    } else {
                        followersView.showMessage(activity, getUserRequestExample.getMessage());
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

      public  static void rejectrequestMethod(Activity activity, String id) {
        if (Utils.isNetworkConnected(activity)) {
//            followersView.showDialog(activity);
            String token = CSPreferences.readString(activity, Utils.TOKEN);
            WebServices.getmInstance().rejectrequest(token, id, new RejectRequestHandler() {
                @Override
                public void onSuccess(RejectRequestExample rejectRequestExample) {
                    followersView.hideDialog();
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


    public  static void acceptrequest(Activity activity, String id){
        if(Utils.isNetworkConnected(activity)){
//            followersView.showDialog(activity);
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
