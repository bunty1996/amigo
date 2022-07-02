package com.app.amigo.Handler;

import com.app.amigo.Models.Message.GetFriendList.GetFriendListExample;

public interface GetFriendListHandler {
    public void onSuccess(GetFriendListExample getFriendListExample);
    public void onError(String message);
}
