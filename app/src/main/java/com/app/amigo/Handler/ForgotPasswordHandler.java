package com.app.amigo.Handler;

import com.app.amigo.Models.Additionalinformation.AditionalInformationExample;
import com.app.amigo.Models.ForgotPassword.ForgotPasswordExample;

public interface ForgotPasswordHandler {
    public void onSuccess(ForgotPasswordExample forgotPasswordExample);

    public void onError(String message);
}
