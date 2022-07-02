package com.app.amigo.Handler;

import com.app.amigo.Models.Login.LoginExample;

public interface LoginHandler {
    public void onSuccess(LoginExample loginExample, String accessToken);
    public void onError(String message);
}
