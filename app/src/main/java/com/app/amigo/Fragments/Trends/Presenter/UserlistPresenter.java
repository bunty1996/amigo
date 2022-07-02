package com.app.amigo.Fragments.Trends.Presenter;

import android.app.Activity;

import androidx.fragment.app.FragmentActivity;

import com.app.amigo.Adapters.CardStackAdapter;
import com.app.amigo.Fragments.Trends.View.TrendsView;
import com.app.amigo.Handler.GetUserlistHandler;
import com.app.amigo.Handler.UserlistInterface;
import com.app.amigo.Models.Trends.Userlist.TrendsDatum;
import com.app.amigo.Models.Trends.Userlist.TrendsExample;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UserlistPresenter {
    private Activity activity;
    private TrendsView trendsView;
    private UserlistInterface userlistInterface;
    private CardStackAdapter adapter;

    public UserlistPresenter(@Nullable FragmentActivity activity, @NotNull TrendsView trendsView) {
        this.activity = activity;
        this.trendsView = trendsView;

    }

    public void getUserlist(CardStackAdapter adapter) {

        this.adapter = adapter;

        String token = CSPreferences.readString(activity, Utils.TOKEN);

        if (Utils.isNetworkConnected(activity)) {
            trendsView.showDialog(activity);
            WebServices.getmInstance().getUserlist(1, 20, token, new GetUserlistHandler() {
                @Override
                public void onSuccess(TrendsExample userlistExample) {
                    trendsView.hideDialog();
                    if (userlistExample != null) {
                        if (userlistExample.getIsSuccess() == true) {


//                          CSPreferences.putString(activity,Utils.ADDRESS,userlistExample.getData());
                            addlist(userlistExample.getData());
                            trendsView.setData(userlistExample.getData());
                        }
                    } else {
                        trendsView.showMessage(activity, userlistExample.getMessage());
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

    private void addlist(List<TrendsDatum> data) {

        adapter.ViewClickListner(new CardStackAdapter.OnViewClick() {
            @Override
            public void onitemclick(int position) {

                trendsView.setposition(position);
            }
        });

    }

//    override fun addlist(data: MutableList<UserListDatum>?) {
//        adapter.ViewClickListner(object : CardStackAdapter.OnViewClick {
//            override fun onitemclick(position: Int) {
//
//                this.position123 = position
//
//            }
//        })
//    }
}
