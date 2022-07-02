package com.app.amigo.Fragments.ResetPassword;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.PasswordTransformationMethod;
import android.text.method.SingleLineTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.amigo.Activities.SelectGender.SelectGenderActivity;
import com.app.amigo.Fragments.ResetPassword.Presenter.ResetPasswordPresenter;
import com.app.amigo.Fragments.ResetPassword.View.ResetPasswordView;
import com.app.amigo.R;
import com.app.amigo.Utility;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;

public class ResetPasswordFragment extends Fragment implements View.OnClickListener, ResetPasswordView {
    private View view;
    private Activity activity;
    private ImageView img_eye;
    private EditText et_oldpassword;
    private EditText et_newpassword;
    Boolean flag = true;
    ResetPasswordPresenter resetPasswordPresenter;
    private Button btn_resetpassword;
    private String gender;
    private LinearLayout linearlayout;
    private TextView tv_changepassword;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        changetheme();
        view = inflater.inflate(R.layout.fragment_reset_password, container, false);
        activity = getActivity();
        init();
        listeners();
        customtheme();
        resetPasswordPresenter = new ResetPasswordPresenter(activity, this);
        return view;
    }


    private void init() {
        img_eye = view.findViewById(R.id.img_eye);
        et_oldpassword = view.findViewById(R.id.et_Password);
        et_newpassword = view.findViewById(R.id.et_ConfirmPassword);
        btn_resetpassword = view.findViewById(R.id.btn_resetpassword);
        tv_changepassword = view.findViewById(R.id.tv_changepassword);
        linearlayout = view.findViewById(R.id.linearlayout);
    }

    private void listeners() {
        img_eye.setOnClickListener(this);
        btn_resetpassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == img_eye) {
            if (flag) {
                img_eye.setImageResource(R.drawable.eyes2);
                if (et_newpassword.getTransformationMethod().getClass().getSimpleName().equals("PasswordTransformationMethod")) {
                    et_newpassword.setTransformationMethod(new SingleLineTransformationMethod());
                } else {
                    et_newpassword.setTransformationMethod(new PasswordTransformationMethod());
                }

                et_newpassword.setSelection(et_newpassword.getText().length());

            } else {
                img_eye.setImageResource(R.drawable.eyes);
                et_newpassword.setTransformationMethod(new PasswordTransformationMethod());

            }
            flag = !flag;
        } else if (v == btn_resetpassword) {
            resetPasswordPresenter.resetpassword(et_oldpassword, et_newpassword);
        }
    }

    @Override
    public void showDialog(Activity activity) {
        Utils.showDialogMethod(activity,activity.getFragmentManager());


    }

    @Override
    public void hideDialog() {
        Utils.hideDialog();


    }

    @Override
    public void showMessage(Activity activity, String msg) {
        Utils.showMessage(activity,msg);

    }

    private void changetheme(){
        gender = CSPreferences.readString(getActivity(), Utils.GENDERSELECT);
        if(gender.equalsIgnoreCase("Male")){
            getActivity().setTheme(R.style.Maletheme);
            Utility.onActivityCreateSetTheme(getActivity());
        }else{
            getActivity().setTheme(R.style.Femaletheme);

        }
    }
    private void customtheme(){
        if(gender.equalsIgnoreCase("Male")){
            activity.setTheme(R.style.Maletheme);
            linearlayout.setBackgroundColor(getResources().getColor(R.color.maleblueTheme));
//            SelectGenderActivity.indicatorSeekBar.setBackgroundColor(getResources().getColor(R.color.maleblueTheme));
            getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.maleblueTheme));
            btn_resetpassword.setBackgroundColor(getResources().getColor(R.color.malebuttoncolor));

            Utility.onActivityCreateSetTheme(getActivity());
        }else{
            activity.setTheme(R.style.Femaletheme);
            linearlayout.setBackgroundColor(getResources().getColor(R.color.femalepinkTheme));
            getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.femalepinkTheme));

        }


    }
}