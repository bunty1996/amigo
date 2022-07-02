package com.app.amigo.Handler;

import com.app.amigo.Models.Followers.GetUserRequest.GetUserRequestExample;

public interface GetUserRequestHandler {
    public void onSuccess(GetUserRequestExample getUserRequestExample);
    public void onError(String message);
}
