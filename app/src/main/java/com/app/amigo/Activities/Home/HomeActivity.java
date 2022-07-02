package com.app.amigo.Activities.Home;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.amigo.Activities.SelectGender.SelectGenderActivity;
import com.app.amigo.Activities.Setting.SettingActivity;
import com.app.amigo.Fragments.Favourite.FavouriteFragment;
import com.app.amigo.Fragments.Followers.FollowersFragment;
import com.app.amigo.Fragments.Message.MessageFragment;
import com.app.amigo.Fragments.Profile.ProfileFragment;
import com.app.amigo.Fragments.Trends.TrendsFragment;
import com.app.amigo.R;
import com.app.amigo.Utility;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayDeque;
import java.util.Deque;


public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomnavigation;
    FrameLayout home_Container;
    private Activity activity;
    private LinearLayout linearlayout;
    private String gender;
    private String setting;
    boolean doubleBackToExitPressedOnce = false;
    private String key = "0";
    private Deque<Integer> fragmentIds = new ArrayDeque<>(3);
    int itemId;
    private TrendsFragment trendsFragment = new TrendsFragment();
    private MessageFragment messageFragment = new MessageFragment();
    private FollowersFragment followersFragment = new FollowersFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
        super.onCreate(savedInstanceState);
        changetheme();
        activity = this;
        setContentView(R.layout.activity_home);
        init();
        listeners();
        customtheme();

        setting = getIntent().getStringExtra("Setting");
        bottomnavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        bottomnavigation.getMenu().findItem(R.id.message).setChecked(true);
        if (setting.equalsIgnoreCase("")) {
            openFragment(new MessageFragment());
            bottomnavigation.getMenu().findItem(R.id.message).setChecked(true);
        }
        if (setting.equalsIgnoreCase("sett")) {
            openFragment(new ProfileFragment());
            bottomnavigation.getMenu().findItem(R.id.profile).setChecked(true);

        } else if (setting.equalsIgnoreCase("")) {
            openFragment(new MessageFragment());
            bottomnavigation.getMenu().findItem(R.id.message).setChecked(true);

        } else if (setting.equalsIgnoreCase("trends")) {
            openFragment(new TrendsFragment());
            bottomnavigation.getMenu().findItem(R.id.trending).setChecked(true);
        } else if (setting.equalsIgnoreCase("fav")) {
            openFragment(new FavouriteFragment());
            bottomnavigation.getMenu().findItem(R.id.fav).setChecked(true);
        } else if (setting.equalsIgnoreCase("req")) {
            openFragment(new FollowersFragment());
            bottomnavigation.getMenu().findItem(R.id.request).setChecked(true);
        }

    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Boolean flag = false;
//                    if(flag==)
                    switch (item.getItemId()) {
                        case R.id.trending:


                            openFragment(new TrendsFragment());
                            return true;
                        case R.id.message:
//                            setFragment(messageFragment, "2", 1);
                            openFragment(new MessageFragment());
                            return true;
                        case R.id.request:
                            openFragment(new FollowersFragment());
//                            setFragment(followersFragment, "3", 2);
                            return true;
                        case R.id.fav:
//                            setFragment(favouriteFragment, "4", 3);
                            openFragment(new FavouriteFragment());
                            return true;
                        case R.id.profile:
//                            setFragment(profileFragment, "5", 4);
                            openFragment(new ProfileFragment());
                            return true;
                    }
                    return false;
                }
            };


    private void init() {
        bottomnavigation = findViewById(R.id.bottomnavigation);
        home_Container = findViewById(R.id.home_Container);
        linearlayout = findViewById(R.id.linearlayout);

    }

    private void listeners() {
    }


    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_Container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private void setLightStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = activity.getWindow().getDecorView().getSystemUiVisibility(); // get current flag
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   // add LIGHT_STATUS_BAR to flag
            activity.getWindow().getDecorView().setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(getResources().getColor(R.color.femalebackgroundcolor)); // optional
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
            bottomnavigation.setBackgroundColor(getResources().getColor(R.color.malebottomnavcolor));
            Utility.onActivityCreateSetTheme(this);

        } else {
            setTheme(R.style.Femaletheme);
            linearlayout.setBackgroundColor(getResources().getColor(R.color.femalepinkTheme));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bottom_navigation, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.message) {
            startActivity(new Intent(activity, SettingActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
//                super.onBackPressed();
            finishAffinity();
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        Snackbar.make(findViewById(R.id.linearlayout), "Please click back again to exit", Snackbar.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


    private void showTabWithoutAddingToBackStack(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.home_Container, fragment, fragment.getClass().getSimpleName()).commit();
    }


}
