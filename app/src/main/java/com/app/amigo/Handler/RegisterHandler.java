package com.app.amigo.Handler;

import com.app.amigo.Models.Signup.RegisterExample;

public interface RegisterHandler {
    public void onSuccess(RegisterExample registerExample);
    public void onError(String message);
}
