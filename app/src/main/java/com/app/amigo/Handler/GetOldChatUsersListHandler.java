package com.app.amigo.Handler;

import com.app.amigo.Models.Message.GetOldChatList.GetOldChatUsersListExample;

public interface GetOldChatUsersListHandler {

    public void onSuccess(GetOldChatUsersListExample getOldChatUsersListExample );
    public void onError(String message);
}
