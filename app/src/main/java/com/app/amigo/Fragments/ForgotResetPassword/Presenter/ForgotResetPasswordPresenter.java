package com.app.amigo.Fragments.ForgotResetPassword.Presenter;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import com.app.amigo.Fragments.ForgotResetPassword.ForgotResetPasswordFragment;
import com.app.amigo.Fragments.ForgotResetPassword.View.ForgotResetPasswordView;
import com.app.amigo.Fragments.LoginFragment.LoginFragment;
import com.app.amigo.Handler.ForgotResetPasswordHandler;
import com.app.amigo.Models.ForgotResetPassword.ForgotResetPasswordExample;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

public class ForgotResetPasswordPresenter {
    private Activity activity;
    private ForgotResetPasswordView forgotResetPasswordView;
    private EditText et_newpassword;
    private EditText et_repeatpassword;

    public ForgotResetPasswordPresenter(Activity activity, ForgotResetPasswordView forgotResetPasswordView) {
        this.activity = activity;
        this.forgotResetPasswordView = forgotResetPasswordView;


    }


    public void forgotResetPassword(EditText et_newpassword, EditText et_repeatpassword) {
        this.et_newpassword = et_newpassword;
        this.et_repeatpassword = et_repeatpassword;
        if (Utils.isNetworkConnected(activity)) {
            if (checkValidation()) {
                forgotResetPasswordView.showDialog(activity);
                String token = CSPreferences.readString(activity, Utils.FORGOTPASSWORDTOKEN);
                WebServices.getmInstance().forgotResetPassword(token, et_newpassword.getText().toString().trim(), new ForgotResetPasswordHandler() {
                    @Override
                    public void onSuccess(ForgotResetPasswordExample forgotResetPasswordExample) {
                        forgotResetPasswordView.hideDialog();
                        if (forgotResetPasswordExample != null) {
                            if (forgotResetPasswordExample.getIsSuccess() == true) {
                                forgotResetPasswordView.showMessage(activity, forgotResetPasswordExample.getMessage());
                                Utils.loginActivitychangeFragment(activity, new LoginFragment());
                            }
                            forgotResetPasswordView.showMessage(activity, forgotResetPasswordExample.getMessage());
                        } else {
                            forgotResetPasswordView.showMessage(activity, forgotResetPasswordExample.getMessage());
                        }
                    }

                    @Override
                    public void onError(String message) {
                        forgotResetPasswordView.hideDialog();
                        forgotResetPasswordView.showMessage(activity, message);

                    }
                });

            }
        }

    }

    private boolean checkValidation() {
        String new_Pass = et_newpassword.getText().toString();
        String confrmPass = et_repeatpassword.getText().toString();
        if (new_Pass.toString().trim().length() == 0) {
            forgotResetPasswordView.showMessage(activity, "Please enter register phone number");
            return false;
        } else if (!new_Pass.equals(confrmPass)) {
            Toast.makeText(activity, "Password Not matching", Toast.LENGTH_SHORT).show();
            return false;


        }
        return true;
    }
}
