package com.app.amigo.Activities.Setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.amigo.Activities.Home.HomeActivity;
import com.app.amigo.Activities.Login.LoginActivity;
import com.app.amigo.Activities.SelectGender.SelectGenderActivity;
import com.app.amigo.Activities.Setting.Presenter.SettingPresenter;
import com.app.amigo.Activities.Setting.View.SettingView;
import com.app.amigo.Fragments.Personalinformation.PersonalinformationFragment;
import com.app.amigo.Fragments.ResetPassword.ResetPasswordFragment;
import com.app.amigo.Models.PersonalProfile.GetCurrentProfileData.PersonalProfileData;
import com.app.amigo.R;
import com.app.amigo.Utility;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener, SettingView {
    private Activity activity;
    private TextView tv_setting;
    private String gender;
    private LinearLayout linearlayout;
    private TextView tv_signout;
    private TextView tv_username;
    private TextView tv_useremail;
    private TextView tv_editprofile;
    private String name;
    private String email;
    private CircleImageView img_profileimage;
    private SettingPresenter settingPresenter;
    private PersonalProfileData data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changetheme();
        setContentView(R.layout.activity_setting);
        activity = this;
        init();
        listeners();
        customtheme();
        settingPresenter = new SettingPresenter(activity, this);
        settingPresenter.personalprofileData();
//
//        name = getIntent().getStringExtra("name");
//        email = getIntent().getStringExtra("email");
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            String resId = bundle.getString("image");
//

    }

    private void init() {
        tv_setting = findViewById(R.id.tv_setting);
        linearlayout = findViewById(R.id.linearlayout);
        tv_signout = findViewById(R.id.tv_signout);
        tv_username = findViewById(R.id.tv_username);
        tv_useremail = findViewById(R.id.tv_useremail);
        tv_editprofile = findViewById(R.id.tv_editprofile);
        img_profileimage = findViewById(R.id.img_profileimage);

    }


    private void listeners() {
        tv_setting.setOnClickListener(this);
        tv_signout.setOnClickListener(this);
        tv_editprofile.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == tv_setting) {
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new ResetPasswordFragment()).commit();
        } else if (v == tv_signout) {
            showDialog();

        } else if (v == tv_editprofile) {
            Intent intent = new Intent(activity, SelectGenderActivity.class);
            startActivity(intent);

//            PersonalinformationFragment fragment = new PersonalinformationFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString("setting", "editprofile");
//            fragment.setArguments(bundle);
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.add(android.R.id.content, fragment);
//            fragmentTransaction.commit();


//
//            getSupportFragmentManager().beginTransaction()
//                    .add(android.R.id.content, new PersonalinformationFragment())
//                    .commit();

        }
    }

    private void changetheme() {
        gender = CSPreferences.readString(this, Utils.GENDERSELECT);
        if (gender.equalsIgnoreCase("Male")) {
            setTheme(R.style.Maletheme);
            Utility.onActivityCreateSetTheme(this);
        } else {
            setTheme(R.style.Femaletheme);

        }
    }

    private void customtheme() {
        if (gender.equalsIgnoreCase("Male")) {
            setTheme(R.style.Maletheme);
            linearlayout.setBackgroundColor(getResources().getColor(R.color.maleblueTheme));
//            SelectGenderActivity.indicatorSeekBar.setBackgroundColor(getResources().getColor(R.color.maleblueTheme));
            Utility.onActivityCreateSetTheme(this);
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(activity, R.color.maleblueTheme));

        } else {
            setTheme(R.style.Femaletheme);
            linearlayout.setBackgroundColor(getResources().getColor(R.color.femalepinkTheme));
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(activity, R.color.lightpinkcolor));

        }

    }

    @Override
    public void onBackPressed() {


        Intent intent = new Intent(activity, HomeActivity.class);
        intent.putExtra("Setting", "sett");
        startActivity(intent);
        finish();
//        finish();
        super.onBackPressed();
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.logoutdialog);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();

        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        Button btn_signout = (Button) dialog.findViewById(R.id.btn_signout);
        String gender = CSPreferences.readString(activity, Utils.GENDERSELECT);

        if (gender.equalsIgnoreCase("male")) {
            btn_signout.setBackground(getResources().getDrawable(R.drawable.btnroundshape));
//            btn_signout.setBackgroundColor(getResources().getColor(R.color.malebuttoncolor));


        } else if (gender.equalsIgnoreCase("female")) {
            btn_signout.setBackground(getResources().getDrawable(R.drawable.femalebuttonroundshape));
//            btn_signout.setBackgroundColor(getResources().getColor(R.color.pinkTheme));


        }
        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CSPreferences.putString(activity, Utils.USERLOGIN, "false");
                Intent intent1 = new Intent(activity, LoginActivity.class);
                startActivity(intent1);
                finish();

            }
        });


        Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

//    private void setData() {
//        String names = CSPreferences.readString(activity, Utils.NAME);
//        String emails = CSPreferences.readString(activity, Utils.USEREMAIL);
//
//        tv_username.setText(names);
//        tv_useremail.setText(emails);
//
//    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void showDialog(Activity activity) {
        Utils.showDialogMethod(activity, activity.getFragmentManager());

    }

    @Override
    public void hideDialog() {
        Utils.hideDialog();

    }

    @Override
    public void showMessage(Activity activity, String msg) {
        Utils.showMessage(activity, msg);
    }

    @Override
    public void setData(PersonalProfileData data) {
        this.data = data;
        tv_username.setText(data.getName());
        tv_useremail.setText(data.getEmail());


        if (data.getProfileImageName().isEmpty()) {
            img_profileimage.setImageResource(R.drawable.userdummy);
        } else {
            String userimage = CSPreferences.readString(activity, Utils.USERPHOTO);

            Picasso.with(activity).load(userimage).placeholder(R.drawable.userdummy).into(img_profileimage);

        }

    }


}

