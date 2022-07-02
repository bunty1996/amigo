package com.app.amigo.Handler;

import com.app.amigo.Models.ResetPassword.ResetPasswordExample;

public interface ResetPasswordHandler {
    public void onSuccess(ResetPasswordExample resetPasswordExample);
    public void onError(String message);
}
