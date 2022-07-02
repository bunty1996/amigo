package com.app.amigo.Fragments.Signup.Presenter;


import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.app.amigo.Activities.Home.HomeActivity;
import com.app.amigo.Activities.SelectGender.SelectGenderActivity;
import com.app.amigo.Fragments.LoginFragment.LoginFragment;
import com.app.amigo.Fragments.Signup.SignupFragment;
import com.app.amigo.Fragments.Signup.View.SignUpView;
import com.app.amigo.Handler.RegisterHandler;
import com.app.amigo.Models.Signup.RegisterExample;
import com.app.amigo.Utils.Utils;
import com.app.amigo.Utils.WebServices;

public class SignUpPresenter {
    private Activity activity;
    private SignUpView signUpView;
    private EditText et_SignupNumber;
    private EditText et_Password;
    private CharSequence target;


    public SignUpPresenter(Activity activity, SignUpView signUpView) {
        this.activity = activity;
        this.signUpView = signUpView;


    }

    public void registermethod(EditText et_SignupNumber, EditText et_Password) {
        this.et_SignupNumber = et_SignupNumber;
        this.et_Password = et_Password;
        if (Utils.isNetworkConnected(activity)) {
            if (checkValidation()) {
                signUpView.showDialog(activity);
                WebServices.getmInstance().registermethod(et_SignupNumber.getText().toString().trim(), et_Password.getText().toString().trim(), new RegisterHandler() {
                    @Override
                    public void onSuccess(RegisterExample registerExample) {
                        signUpView.hideDialog();
                        if (registerExample != null) {
                            if (registerExample.getIsSuccess() == true) {
                                signUpView.showMessage(activity, registerExample.getMessage());
                                Utils.loginActivitychangeFragment(activity, new LoginFragment());


//                        Intent intent = new Intent(activity, SelectGenderActivity.class);
//                        activity.startActivity(intent);
//                        activity.finish();

                            } else {
                                signUpView.showMessage(activity, registerExample.getMessage());

                            }

                        } else {
                            signUpView.showMessage(activity, registerExample.getMessage());
                        }

                    }

                    @Override
                    public void onError(String message) {
                        signUpView.hideDialog();
                        signUpView.showMessage(activity, message);

                    }
                });

            } else {
                Toast.makeText(activity, "Please enter valid email address", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(activity, "Please check internet connection", Toast.LENGTH_SHORT).show();

        }
    }

    private boolean checkValidation() {
        if (et_SignupNumber.getText().toString().trim().length() == 0) {
            signUpView.showMessage(activity, "Please enter  phone number");
            return false;

        } else if (et_Password.getText().toString().trim().length() == 0) {
            signUpView.showMessage(activity, "Please enter password");
            return false;
        }
        return true;


    }


}



