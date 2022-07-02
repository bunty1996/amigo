package com.app.amigo.Activities.SelectGender;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.app.amigo.Activities.Login.LoginActivity;
import com.app.amigo.Fragments.Personalinformation.PersonalinformationFragment;
import com.app.amigo.Fragments.SelectGender.SelectGenderFragment;
import com.app.amigo.R;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.warkiz.widget.IndicatorSeekBar;

public class SelectGenderActivity extends AppCompatActivity {


    private Activity activity;

    private FrameLayout selGender_container;
    public static IndicatorSeekBar indicatorSeekBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
//        onResume();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_gender);
        activity = this;
//
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            value = bundle.getString("selgenderfragment");
//        }


        indicatorSeekBar = findViewById(R.id.indicatorSeekBar);
        indicatorSeekBar.setMax(100);
        selGender_container = findViewById(R.id.selGender_container);
        loadfragment(new SelectGenderFragment());

    }

    private void loadfragment(SelectGenderFragment selectGenderFragment) {
        if (selectGenderFragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.selGender_container, selectGenderFragment).commit();


//            SelectGenderFragment  fragment = new SelectGenderFragment();
//            Bundle bundle = new Bundle();
////            bundle.putString("gender", "male");
//            bundle.putString("selgender", "value");
//            fragment.setArguments(bundle);
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.selGender_container, fragment);
//            fragmentTransaction.commit();

        }
    }

    private void setLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = activity.getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   // add LIGHT_STATUS_BAR to flag
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(getResources().getColor(R.color.darkblue)); // optional
        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        String gender = CSPreferences.readString(activity, Utils.GENDERSELECT);
//        if (gender.equalsIgnoreCase("Male")) {
//            activity.setTheme(R.style.MaleTheme);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                int flags = activity.getWindow().getDecorView().getSystemUiVisibility(); // get current flag
//                flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   // add LIGHT_STATUS_BAR to flag
//                activity.getWindow().getDecorView().setSystemUiVisibility(flags);
//                activity.getWindow().setStatusBarColor(getResources().getColor(R.color.maleblueTheme)); // optional
//            }
//
//        } else if (gender.equalsIgnoreCase("Female")) {
//            activity.setTheme(R.style.FemaleTheme);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                int flags = activity.getWindow().getDecorView().getSystemUiVisibility(); // get current flag
//                flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   // add LIGHT_STATUS_BAR to flag
//                activity.getWindow().getDecorView().setSystemUiVisibility(flags);
//                activity.getWindow().setStatusBarColor(getResources().getColor(R.color.femalepinkTheme)); // optional
//            }
//
//        }
////        else {
////            activity.setTheme(R.style.Theme_Amigo);
////
////
////        }
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();

    }
}