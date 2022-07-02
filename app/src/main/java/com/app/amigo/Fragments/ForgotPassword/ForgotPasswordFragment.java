package com.app.amigo.Fragments.ForgotPassword;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.app.amigo.Fragments.ForgotPassword.Presenter.ForgotPasswordPresenter;
import com.app.amigo.Fragments.ForgotPassword.View.ForgotPasswordView;
import com.app.amigo.Fragments.OtpFragment.OTPFragment;
import com.app.amigo.R;
import com.app.amigo.Utils.Utils;


public class ForgotPasswordFragment extends Fragment implements View.OnClickListener, ForgotPasswordView {
    private Activity activity;
    private View view;
    private EditText et_email;
    private Button btn_forgotpassword;
    private ForgotPasswordPresenter forgotPasswordPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        activity = getActivity();
        init();
        listiners();
        forgotPasswordPresenter = new ForgotPasswordPresenter(activity,this);


        return view;
    }


    private void init() {
        et_email = view.findViewById(R.id.et_email);
        btn_forgotpassword = view.findViewById(R.id.btn_forgotpassword);


    }

    private void listiners() {
        btn_forgotpassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==btn_forgotpassword){
//            Utils.loginActivitychangeFragment(activity,new OTPFragment());
          forgotPasswordPresenter.forgotpasswordmethod(et_email);

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
}