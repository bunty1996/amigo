package com.app.amigo.Fragments.Message.Presenter;

import android.app.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.amigo.Adapters.MessagesRecylerAdapter;
import com.app.amigo.Adapters.NewMatchesRecyclerAdapter;
import com.app.amigo.Fragments.Message.View.MessageView;
import com.app.amigo.Handler.GetFriendListHandler;
import com.app.amigo.Handler.GetOldChatUsersListHandler;
import com.app.amigo.Models.Message.GetFriendList.GetFriendListExample;
import com.app.amigo.Models.Message.GetOldChatList.GetOldChatUsersListExample;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

public class MessagePresenter {
    private final RecyclerView newMatches_Recyclerview;
    private final RecyclerView Messages_Recyclerview;
    private Activity activity;
    private MessageView messageView;
    private NewMatchesRecyclerAdapter newMatchesRecyclerAdapter;
    private MessagesRecylerAdapter messagesRecylerAdapter;

    public MessagePresenter(Activity activity, MessageView messageView, RecyclerView newMatches_Recyclerview ,RecyclerView Messages_Recyclerview) {
        this.activity = activity;
        this.messageView = messageView;
        this.newMatches_Recyclerview = newMatches_Recyclerview;
        this.Messages_Recyclerview = Messages_Recyclerview;

    }

    public void getOldChatListUsers() {
        if (Utils.isNetworkConnected(activity)) {
//            messageView.showDialog(activity);
            String id = CSPreferences.readString(activity, Utils.USERID);
            WebServices.getmInstance().getOldChatconversationUserList(id,new GetOldChatUsersListHandler() {
                @Override
                public void onSuccess(GetOldChatUsersListExample getOldChatUsersListExample ) {
                    if (getOldChatUsersListExample.getIsSuccess() == true) {
//                        messageView.showMessage(activity,getOldChatExample.get);

                        messagesRecylerAdapter = new MessagesRecylerAdapter(activity, getOldChatUsersListExample.getData() );
                        Messages_Recyclerview.setHasFixedSize(true);
                        Messages_Recyclerview.setLayoutManager(new LinearLayoutManager(activity));
                        Messages_Recyclerview.setAdapter(messagesRecylerAdapter);

                    }
                }

                @Override
                public void onError(String message) {
                    messageView.hideDialog();
                    messageView.showMessage(activity, message);

                }
            });

        }

    }


    public void getFriendListMethod() {
        if (Utils.isNetworkConnected(activity)) {
//            messageView.showDialog(activity);
            String id = CSPreferences.readString(activity, Utils.USERID);
            String token = CSPreferences.readString(activity, Utils.TOKEN);
            WebServices.getmInstance().getFriendList(token, id, new GetFriendListHandler() {
                @Override
                public void onSuccess(GetFriendListExample getFriendListExample) {
                    messageView.hideDialog();
                    if (getFriendListExample != null) {
                        if (getFriendListExample.getIsSuccess() == true) {
//                            messageView.showMessage(activity,getFriendListExample.getMessage());

                            newMatchesRecyclerAdapter = new NewMatchesRecyclerAdapter(activity, getFriendListExample.getData());
                            newMatches_Recyclerview.setHasFixedSize(true);
                            newMatches_Recyclerview.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
                            newMatches_Recyclerview.setAdapter(newMatchesRecyclerAdapter);
                        }

                    } else {
                        messageView.showMessage(activity, getFriendListExample.getMessage());
                    }
                }

                @Override
                public void onError(String message) {
                    messageView.hideDialog();
                    messageView.showMessage(activity, message);

                }
            });
        }

    }


}
