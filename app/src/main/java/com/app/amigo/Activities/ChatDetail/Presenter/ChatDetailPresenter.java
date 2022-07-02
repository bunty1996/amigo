package com.app.amigo.Activities.ChatDetail.Presenter;

import android.app.Activity;
import android.view.View;

import com.app.amigo.Activities.ChatDetail.ChatDetailActivity;
import com.app.amigo.Activities.ChatDetail.View.ChatDetailView;
import com.app.amigo.Handler.GetOldChatHandler;
import com.app.amigo.Models.GetOldChat.GetOldChatExample;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

public class ChatDetailPresenter {
    private Activity activity;
    private View view;
    private ChatDetailView chatDetailView;

    public ChatDetailPresenter(Activity activity, ChatDetailView chatDetailView) {
        this.activity = activity;
        this.chatDetailView = chatDetailView;

    }


    public void getOldchatList(String toUser) {
        if (Utils.isNetworkConnected(activity)) {
            chatDetailView.showDialog(activity);
//            String conversationid = "";
            WebServices.getmInstance().getOldChatconversations(toUser, 1, 100, new GetOldChatHandler() {
                @Override
                public void onSuccess(GetOldChatExample getOldChatExample) {
                    if (getOldChatExample != null) {
                        chatDetailView.hideDialog();
                        if (getOldChatExample.getIsSuccess() == true) {
//                            messageView.showMessage(activity,getOldChatExample.g);
//                            chatDetailView.setData(getOldChatExample.getItems());
                        }
                    } else {

                    }
                }

                @Override
                public void onError(String message) {
                    chatDetailView.hideDialog();
                    chatDetailView.showMessage(activity, message);
                }
            });

        }
    }

}
