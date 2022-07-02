package com.app.amigo.Fragments.ForgotPassword.Presenter;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.EditText;

import com.app.amigo.Fragments.ForgotPassword.View.ForgotPasswordView;
import com.app.amigo.Fragments.OtpFragment.OTPFragment;
import com.app.amigo.Handler.ForgotPasswordHandler;
import com.app.amigo.Models.Additionalinformation.AditionalInformationExample;
import com.app.amigo.Models.ForgotPassword.ForgotPasswordExample;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

public class ForgotPasswordPresenter {
    private Activity activity;
    private ForgotPasswordView forgotPasswordView;
    private EditText et_email;

    public ForgotPasswordPresenter(Activity activity, ForgotPasswordView forgotPasswordView) {
        this.activity = activity;
        this.forgotPasswordView = forgotPasswordView;

    }

    public void forgotpasswordmethod(EditText et_email) {
        this.et_email = et_email;
        if (Utils.isNetworkConnected(activity)) {
            if (checkValidation()) {
                forgotPasswordView.hideDialog();
                WebServices.getmInstance().forgotpassword(et_email.getText().toString().trim(), new ForgotPasswordHandler() {
                    @Override
                    public void onSuccess(ForgotPasswordExample forgotPasswordExample) {
                        if (forgotPasswordExample != null) {
                            if (forgotPasswordExample.getIsSuccess() == true) {
                                forgotPasswordView.showMessage(activity, forgotPasswordExample.getMessage());
                                Utils.loginActivitychangeFragment(activity,new OTPFragment());
                                CSPreferences.putString(activity,Utils.FORGOTPASSWORDTOKEN,forgotPasswordExample.getData().getToken());
                            }

                            forgotPasswordView.showMessage(activity, forgotPasswordExample.getMessage());

                        } else {
                            forgotPasswordView.showMessage(activity, forgotPasswordExample.getMessage());
//

                        }
                    }

                    @Override
                    public void onError(String message) {
                        forgotPasswordView.hideDialog();
                        forgotPasswordView.showMessage(activity, message);

                    }
                });

            }
        }
    }

    private boolean checkValidation() {
        if (et_email.getText().toString().trim().length() == 0) {
            forgotPasswordView.showMessage(activity, "Please enter email");
            return false;
        } else if (!(isValidEmail(et_email.getText().toString().trim()))) {
            forgotPasswordView.showMessage(activity, "Please enter valid email");

        }


        return true;
    }


    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email)
                && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
