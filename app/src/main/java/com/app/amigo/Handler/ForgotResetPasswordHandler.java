package com.app.amigo.Handler;

import com.app.amigo.Models.ForgotPassword.ForgotPasswordExample;
import com.app.amigo.Models.ForgotResetPassword.ForgotResetPasswordExample;

public interface ForgotResetPasswordHandler {
    public void onSuccess(ForgotResetPasswordExample forgotResetPasswordExample);

    public void onError(String message);
}
