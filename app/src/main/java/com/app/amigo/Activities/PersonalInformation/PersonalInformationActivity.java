package com.app.amigo.Activities.PersonalInformation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.app.amigo.Activities.AditionalInformation.AditionalInformationActivity;
import com.app.amigo.Activities.SelectGender.SelectGenderActivity;
import com.app.amigo.R;
import com.app.amigo.Utility;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;

public class PersonalInformationActivity extends AppCompatActivity implements View.OnClickListener {

    private Activity activity;
    private Button btn_continue;
    private String gender;
    private LinearLayout linearlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
        super.onCreate(savedInstanceState);
        changetheme();
        setContentView(R.layout.activity_personal_information);
        activity = this;
        init();
        listiners();
        customtheme();

    }

    private void init() {
        btn_continue = findViewById(R.id.btn_continue);
        linearlayout = findViewById(R.id.linearlayout);
    }

    private void listiners() {
        btn_continue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == btn_continue){
            Intent intent = new Intent(activity, AditionalInformationActivity.class);
            activity.startActivity(intent);
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


    private void changetheme(){
        gender = CSPreferences.readString(this, Utils.GENDERSELECT);
        if(gender.equalsIgnoreCase("Male")){
            setTheme(R.style.Maletheme);
            Utility.onActivityCreateSetTheme(this);
        }else{
            setTheme(R.style.Femaletheme);
            Utility.onActivityCreateSetTheme(this);

        }
    }
    private void customtheme(){
        if(gender.equalsIgnoreCase("Male")){
            setTheme(R.style.Maletheme);
            linearlayout.setBackgroundColor(getResources().getColor(R.color.maleblueTheme));
            SelectGenderActivity.indicatorSeekBar.setBackgroundColor(getResources().getColor(R.color.maleblueTheme));
            Utility.onActivityCreateSetTheme(this);

        }else{
            setTheme(R.style.Femaletheme);
            linearlayout.setBackgroundColor(getResources().getColor(R.color.femalepinkTheme));
            Utility.onActivityCreateSetTheme(this);
        }

    }
}