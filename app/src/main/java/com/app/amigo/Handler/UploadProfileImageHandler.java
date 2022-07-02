package com.app.amigo.Handler;

import com.app.amigo.Models.Profile.UploadProfileimageExample;
import com.app.amigo.Models.ResetPassword.ResetPasswordExample;

public interface UploadProfileImageHandler {
    public void onSuccess(UploadProfileimageExample uploadProfileimageExample);
    public void onError(String message);
}
