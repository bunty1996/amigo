package com.app.amigo.Fragments.ResetPassword.Presenter;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.app.amigo.Activities.Setting.SettingActivity;
import com.app.amigo.Fragments.LoginFragment.View.LoginView;
import com.app.amigo.Fragments.ResetPassword.ResetPasswordFragment;
import com.app.amigo.Fragments.ResetPassword.View.ResetPasswordView;
import com.app.amigo.Handler.ResetPasswordHandler;
import com.app.amigo.Models.ResetPassword.ResetPasswordExample;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

public class ResetPasswordPresenter {
    private Activity activity;
    private ResetPasswordView resetPasswordView;
    private EditText et_password;
    private EditText et_confirmpassword;
    String token;
    String id;

    public ResetPasswordPresenter(Activity activity, ResetPasswordView resetPasswordView) {
        this.activity = activity;
        this.resetPasswordView = resetPasswordView;
    }

    public void resetpassword(EditText et_oldpassword, EditText et_newpassword) {
        this.et_password = et_oldpassword;
        this.et_confirmpassword = et_newpassword;
        if (Utils.isNetworkConnected(activity)) {

            String userId = CSPreferences.readString(activity, Utils.USERID);
            String token = CSPreferences.readString(activity, Utils.TOKEN);

            if (checkvalidation()) {
                resetPasswordView.showDialog(activity);
                WebServices.getmInstance().changePasswordMethod(token, userId, et_oldpassword.getText().toString().trim(), et_newpassword.getText().toString().trim(), new ResetPasswordHandler() {
                    @Override
                    public void onSuccess(ResetPasswordExample resetPasswordExample) {
                        resetPasswordView.hideDialog();
                        if (resetPasswordExample != null) {
                            if (resetPasswordExample.getIsSuccess() == true) {
//                                Utils.changeFragment2(activity,new SettingFragment());
                                Intent intent = new Intent(activity, SettingActivity.class);
                                activity.startActivity(intent);


                            }

                            resetPasswordView.showMessage(activity, resetPasswordExample.getMessage());
                        } else {
                            resetPasswordView.showMessage(activity, resetPasswordExample.getMessage());
                        }
                    }

                    @Override
                    public void onError(String message) {
                        resetPasswordView.hideDialog();
                        resetPasswordView.showMessage(activity, message);

                    }
                });

            }
        }

    }

    private boolean checkvalidation() {
        boolean temp = true;
        String pass = et_password.getText().toString();
        String confirm_pass = et_confirmpassword.getText().toString();
        if (pass.toString().trim().length() == 0) {
            Toast.makeText(activity, "Please enter new password", Toast.LENGTH_SHORT).show();
            temp = false;
        } else if (confirm_pass.toString().trim().length() == 0) {
            Toast.makeText(activity, "Please enter confirm password", Toast.LENGTH_SHORT).show();
            temp = false;
        }
        return temp;
    }

}
