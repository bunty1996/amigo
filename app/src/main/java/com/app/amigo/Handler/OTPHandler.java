package com.app.amigo.Handler;

import com.app.amigo.Models.ForgotPassword.ForgotPasswordExample;
import com.app.amigo.Models.Otp.OtpExample;

public interface OTPHandler {
    public void onSuccess(OtpExample otpExample);

    public void onError(String message);
}
