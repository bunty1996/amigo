package com.app.amigo.Fragments.Signup;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.amigo.Fragments.LoginFragment.LoginFragment;
import com.app.amigo.Fragments.SelectGenderOrientationFragment.SelectgenderOrientationFragment;
import com.app.amigo.Fragments.Signup.Presenter.SignUpPresenter;
import com.app.amigo.Fragments.Signup.View.SignUpView;
import com.app.amigo.R;
import com.app.amigo.Utils.Utils;


public class SignupFragment extends Fragment implements View.OnClickListener, SignUpView {

    private Activity activity;
    private View view;
    private Button btn_signupCreateAccount;
    private TextView txt_login;

    private Boolean flag = true;
    private ImageView img_eye;
    private ImageView img_confirmeye;
    private EditText et_SignupNumber;
    private EditText et_Password;
    private EditText et_ConfirmPassword;
    SignUpPresenter signUpPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_signup, container, false);
        activity = getActivity();
        init();
        listeners();
        signUpPresenter = new SignUpPresenter(activity, this);


        return view;
    }


    private void init() {
        btn_signupCreateAccount = view.findViewById(R.id.btn_signupCreateAccount);
        txt_login = view.findViewById(R.id.txt_login);
        et_Password = view.findViewById(R.id.et_Password);
        et_ConfirmPassword = view.findViewById(R.id.et_ConfirmPassword);
        et_SignupNumber = view.findViewById(R.id.et_SignupNumber);
        img_eye = view.findViewById(R.id.img_eye);
        img_confirmeye = view.findViewById(R.id.img_confirmeye);
    }

    private void listeners() {
        btn_signupCreateAccount.setOnClickListener(this);
        txt_login.setOnClickListener(this);
        et_Password.setOnClickListener(this);
        img_eye.setOnClickListener(this);
        et_ConfirmPassword.setOnClickListener(this);
        img_confirmeye.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == btn_signupCreateAccount) {
            emailValidator(et_SignupNumber);

        } else if (v == txt_login) {
            LoginFragment fragment = new LoginFragment();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.mainContainer, fragment);
            transaction.commit();

        } else if (v == img_eye) {
            if (flag) {
                img_eye.setImageResource(R.drawable.eyes2);
                if (et_Password.getTransformationMethod().getClass().getSimpleName().equals("PasswordTransformationMethod")) {
                    et_Password.setTransformationMethod(new SingleLineTransformationMethod());
                } else {
                    et_Password.setTransformationMethod(new PasswordTransformationMethod());
                }

                et_Password.setSelection(et_Password.getText().length());
            } else {
                img_eye.setImageResource(R.drawable.eyes);
                et_Password.setTransformationMethod(new PasswordTransformationMethod());
            }
            flag = !flag;

        } else if (v == img_confirmeye) {
            if (flag) {
                img_confirmeye.setImageResource(R.drawable.eyes2);
                if (et_ConfirmPassword.getTransformationMethod().getClass().getSimpleName().equals("PasswordTransformationMethod")) {
                    et_ConfirmPassword.setTransformationMethod(new SingleLineTransformationMethod());
                } else {
                    et_ConfirmPassword.setTransformationMethod(new PasswordTransformationMethod());
                }

                et_ConfirmPassword.setSelection(et_ConfirmPassword.getText().length());
            } else {
                img_confirmeye.setImageResource(R.drawable.eyes);
                et_ConfirmPassword.setTransformationMethod(new PasswordTransformationMethod());
            }
            flag = !flag;

        }
    }

    public void emailValidator(EditText etMail) {

        // extract the entered data from the EditText
        String emailToText = etMail.getText().toString();

        // Android offers the inbuilt patterns which the entered
        // data from the EditText field needs to be compared with
        // In this case the the entered data needs to compared with
        // the EMAIL_ADDRESS, which is implemented same below
        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
//            Toast.makeText(activity, "Email Verified !", Toast.LENGTH_SHORT).show();
            signUpPresenter.registermethod(et_SignupNumber, et_Password);
        } else {
            Toast.makeText(activity, "Enter valid Email address ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMessage(Activity activity, String msg) {
        Utils.showMessage(activity,msg);

    }

    @Override
    public void showDialog(Activity activity) {
        Utils.showDialogMethod(activity,activity.getFragmentManager());


    }

    @Override
    public void hideDialog() {
        Utils.hideDialog();


    }
}