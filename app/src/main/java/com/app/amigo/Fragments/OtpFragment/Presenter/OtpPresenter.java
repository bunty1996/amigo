package com.app.amigo.Fragments.OtpFragment.Presenter;

import android.app.Activity;
import android.widget.EditText;

import com.app.amigo.Fragments.ForgotResetPassword.ForgotResetPasswordFragment;
import com.app.amigo.Fragments.OtpFragment.OTPFragment;
import com.app.amigo.Fragments.OtpFragment.View.OtpView;
import com.app.amigo.Handler.OTPHandler;
import com.app.amigo.Models.Otp.OtpExample;
import com.app.amigo.R;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

public class OtpPresenter {
    private Activity activity;
    private OtpView otpView;

    public OtpPresenter(Activity activity, OtpView otpView) {
        this.activity = activity;
        this.otpView = otpView;

    }

    public void otpmethod(String code) {

        String token = CSPreferences.readString(activity, Utils.FORGOTPASSWORDTOKEN);
        if (Utils.isNetworkConnected(activity)) {
            otpView.showDialog(activity);
            WebServices.getmInstance().otpverifcation(token, code, new OTPHandler() {
                @Override
                public void onSuccess(OtpExample otpExample) {
                    otpView.hideDialog();
                    if (otpExample != null) {
                        if (otpExample.getIsSuccess() == true) {
                            otpView.showMessage(activity, otpExample.getMessage());
                            Utils.loginActivitychangeFragment(activity, new ForgotResetPasswordFragment());
                        } else {
                            otpView.showMessage(activity, otpExample.getMessage());
                        }
                    }

                }

                @Override
                public void onError(String message) {
                    otpView.hideDialog();
                    otpView.showMessage(activity, message);

                }
            });


        } else {
            otpView.showMessage(activity, activity.getString(R.string.internet_connection));
        }

    }

}
