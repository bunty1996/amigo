package com.app.amigo.Fragments.LoginFragment.Presenter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.app.amigo.Activities.Home.HomeActivity;
import com.app.amigo.Activities.SelectGender.SelectGenderActivity;
import com.app.amigo.Fragments.LoginFragment.View.LoginView;
import com.app.amigo.Handler.LoginHandler;
import com.app.amigo.Models.Login.LoginExample;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

public class LoginPresenter {
    private Activity activity;
    private LoginView loginView;
    private EditText et_SignupName;
    private EditText et_Password;

    public LoginPresenter(Activity activity, LoginView loginView) {
        this.activity = activity;
        this.loginView = loginView;

    }

    public void loginMethod(EditText et_SignupName, EditText et_Password) {
        this.et_SignupName = et_SignupName;
        this.et_Password = et_Password;
        if (Utils.isNetworkConnected(activity)) {
            if (checkValidation()) {
                loginView.showDialog(activity);
                WebServices.getmInstance().loginMethod(et_SignupName.getText().toString().trim(), et_Password.getText().toString().trim(), new LoginHandler() {
                    @Override
                    public void onSuccess(LoginExample loginExample, String accessToken) {
                        loginView.hideDialog();
                        if (loginExample != null) {
                            if (loginExample.getIsSuccess() == true) {

//                                Log.e("headerToken",accessToken);

                                loginView.showMessage(activity, loginExample.getMessage());

                                CSPreferences.putString(activity, Utils.USERID, loginExample.getData().getId());
                                CSPreferences.putString(activity, Utils.TOKEN, accessToken);

                                CSPreferences.putString(activity, Utils.NAME, loginExample.getData().getName());
                                CSPreferences.putString(activity, Utils.USEREMAIL, loginExample.getData().getEmail());
                                CSPreferences.putString(activity, Utils.ABOUTME, loginExample.getData().getAboutMe());
                                loginView.setData(activity, loginExample.getData());

                                if (loginExample.getData().getSex() == null) {
                                    Intent intent = new Intent(activity, SelectGenderActivity.class);
                                    activity.startActivity(intent);
                                    activity.finish();
                                } else if (loginExample.getData().getName() == null) {
                                    Intent intent = new Intent(activity, SelectGenderActivity.class);
                                    activity.startActivity(intent);
                                    activity.finish();

                                } else {
                                    Intent intent = new Intent(activity, HomeActivity.class);
                                    intent.putExtra("Setting", "");
                                    activity.startActivity(intent);
                                    activity.finish();

                                }

                            } else {
                                loginView.showMessage(activity, loginExample.getMessage());

                            }
                        } else {
                            loginView.showMessage(activity, loginExample.getMessage());
                        }


                    }

                    @Override
                    public void onError(String message) {
                        loginView.hideDialog();
                        loginView.showMessage(activity, message);

                    }
                });


            }


        } else {
            Toast.makeText(activity, "Please Check Internet Connection", Toast.LENGTH_SHORT).show();

        }

    }

    private boolean checkValidation() {
        if (et_SignupName.getText().toString().trim().length() == 0) {
            loginView.showMessage(activity, "Please enter register phone number");
            return false;

        } else if (et_Password.getText().toString().trim().length() == 0) {
            loginView.showMessage(activity, "Please enter password");
            return false;
        }
        return true;

    }
}
