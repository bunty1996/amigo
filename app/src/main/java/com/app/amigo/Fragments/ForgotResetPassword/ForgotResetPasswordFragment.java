package com.app.amigo.Fragments.ForgotResetPassword;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.app.amigo.Fragments.ForgotResetPassword.Presenter.ForgotResetPasswordPresenter;
import com.app.amigo.Fragments.ForgotResetPassword.View.ForgotResetPasswordView;
import com.app.amigo.R;
import com.app.amigo.Utils.Utils;

public class ForgotResetPasswordFragment extends Fragment implements View.OnClickListener, ForgotResetPasswordView {
    private Activity activity;
    private View view;
    private EditText et_newpassword;
    private EditText et_repeatpassword;
    private Button btn_resetpassword;
    private ForgotResetPasswordPresenter forgotResetPasswordPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_forgot_reset_password, container, false);
        activity= getActivity();
        init();
        listeners();

        forgotResetPasswordPresenter = new ForgotResetPasswordPresenter(activity, this);
        return view;
    }

    private void init() {
        et_newpassword = view.findViewById(R.id.et_newpassword);
        et_repeatpassword = view.findViewById(R.id.et_repeatpassword);
        btn_resetpassword = view.findViewById(R.id.btn_resetpassword);

    }

    private void listeners() {
        btn_resetpassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_resetpassword) {
            forgotResetPasswordPresenter.forgotResetPassword(et_newpassword, et_repeatpassword);

        }
    }

    @Override
    public void showMessage(Activity activity, String msg) {
        Utils.showMessage(activity,msg);
    }

    @Override
    public void showDialog(Activity activity) {
        Utils.showDialogMethod(activity, activity.getFragmentManager());
    }

    @Override
    public void hideDialog() {
        Utils.hideDialog();
    }
}