package com.app.amigo.Fragments.Trends.Presenter;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.app.amigo.Fragments.Trends.View.TrendsView;
import com.app.amigo.Handler.AddtoFavHandler;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;
import com.example.AddtoFavExample;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AddtoFavPresenter {
    private Activity activity;
    private TrendsView trendsView;


    public AddtoFavPresenter(@Nullable FragmentActivity activity, @NotNull TrendsView trendsView) {
        this.activity = activity;
        this.trendsView = trendsView;
    }

    public void addtofavMethod(String id) {
        if (Utils.isNetworkConnected(activity)) {
//            trendsView.showDialog(activity);
            String userid = CSPreferences.readString(activity, Utils.USERID);
//            String toLikeUserId = CSPreferences.readString(activity, Utils.ANOTHERUSERID);
            WebServices.getmInstance().addtofav(userid, id, new AddtoFavHandler() {
                @Override
                public void onSuccess(AddtoFavExample addtoFavExample) {
                    trendsView.hideDialog();
                    if (addtoFavExample != null) {
                        if (addtoFavExample.getIsSuccess() == true) {
                            Log.d("checktoast",addtoFavExample.getMessage());
//                            Toast.makeText(activity, "favourite added successfully", Toast.LENGTH_SHORT).show();

                        } else {
//                            trendsView.showMessage(activity, addtoFavExample.getMessage());
                        }
                    } else {
//                        trendsView.showMessage(activity, addtoFavExample.getMessage());


                    }

                }

                @Override
                public void onError(String message) {
                    trendsView.hideDialog();
                    trendsView.showMessage(activity, message);

                }
            });

        }

    }

}
