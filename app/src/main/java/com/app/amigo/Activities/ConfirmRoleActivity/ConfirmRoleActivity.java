package com.app.amigo.Activities.ConfirmRoleActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.amigo.Activities.Login.LoginActivity;
import com.app.amigo.R;

public class ConfirmRoleActivity extends AppCompatActivity implements View.OnClickListener {

    private Activity activity;
    private TextView txt_skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_role);
        activity = this;

        init();
        listiners();
    }

    private void init() {

        txt_skip = findViewById(R.id.txt_skip);
    }

    private void listiners() {

        txt_skip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == txt_skip){
            Intent intent = new Intent(activity, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void setLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = activity.getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   // add LIGHT_STATUS_BAR to flag
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(getResources().getColor(R.color.lightpinkcolor)); // optional
        }
    }
}