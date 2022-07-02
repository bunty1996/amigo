package com.app.amigo.Activities.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.app.amigo.Activities.Home.HomeActivity;
import com.app.amigo.Activities.Login.LoginActivity;
import com.app.amigo.Activities.SelectGender.SelectGenderActivity;
import com.app.amigo.R;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashActivity extends AppCompatActivity {

    public static final int MY_PERMISSIONS_REQUEST_WRITE_FIELS = 102;
    private static final int REQUEST = 112;
    Activity activity;
    private Dialog dialog;

    private SharedPreferences pref;
    private String loginValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        activity = this;
        setLightStatusBar(this);
        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
        init();

        try {
            PackageInfo info = activity.getPackageManager().getPackageInfo(
                    "com.app.amigo",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", "KeyHash:" + Base64.encodeToString(md.digest(),
                        Base64.DEFAULT));

                Log.e("SHA1", String.valueOf(md));

            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    private void init() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String status = CSPreferences.readString(activity, Utils.USERLOGIN);
                if (status.equalsIgnoreCase("true")) {
//                    Intent intent2 = new Intent(activity, HomeActivity.class);
                    Intent intent2 = new Intent(activity, HomeActivity.class);
                    intent2.putExtra("Setting","");
                    startActivity(intent2);
                    finishAffinity();

                } else {
                    Intent intent2 = new Intent(activity, LoginActivity.class);
                    startActivity(intent2);

                }


            }
        }, 1500);
    }

    private void setLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = activity.getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   // add LIGHT_STATUS_BAR to flag
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(getResources().getColor(R.color.red2)); // optional
        }
    }
}