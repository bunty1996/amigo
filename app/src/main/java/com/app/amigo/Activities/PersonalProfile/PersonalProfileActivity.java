package com.app.amigo.Activities.PersonalProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.amigo.Activities.ConfirmRoleActivity.ConfirmRoleActivity;
import com.app.amigo.Activities.Home.HomeActivity;
import com.app.amigo.Activities.PersonalProfile.Presenter.PersonalProfilePresenter;
import com.app.amigo.Activities.PersonalProfile.View.PersonalProfileView;
import com.app.amigo.Adapters.InterestRecyclerAdapter;
import com.app.amigo.Adapters.PprofileViewPager_Adapter;
import com.app.amigo.Adapters.ProfilePhotosRecyclerAdapter;
import com.app.amigo.Models.PersonalProfile.GetCurrentProfileData.PersonalProfileData;
import com.app.amigo.Models.PersonalProfile.InterestModelClass;
import com.app.amigo.R;
import com.app.amigo.Utility;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import me.relex.circleindicator.CircleIndicator;

public class PersonalProfileActivity extends AppCompatActivity implements View.OnClickListener, PersonalProfileView {
    private static int ageInteger = 0;
    private static String calculateAge;
    private Activity activity;
    RecyclerView h_recyclerview;
    ProfilePhotosRecyclerAdapter profilePhotosRecyclerAdapter;
    int[] images = {R.drawable.mensi, R.drawable.mensi, R.drawable.mensi};
    PprofileViewPager_Adapter viewPager_adapter;
    CircleIndicator indicator;
    ViewPager viewpager;
    ImageView img_back;
    private RelativeLayout relativelayout;
    private String gender;

    private RecyclerView recyclerview_interest;
    private InterestRecyclerAdapter interestRecyclerAdapter;

    private PersonalProfilePresenter personalProfilePresenter;
    private TextView tv_username;
    private TextView tv_userage;
    private TextView tv_description;
    private TextView tv_aboutmedetail;
    private ImageView img_userimage;
    private ImageView img_onlinestatus;
    private LinearLayout lin_exit;
    private LinearLayout lin_fav;
    private LinearLayout lin_trends;
    private int foo;
    int day;
    int month;
    int year;
    private PersonalProfileData data;
    private String value;
    private String date;
    private String currentyear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changetheme();
        setContentView(R.layout.activity_personal_profile);
        activity = this;
        init();
        listeners();
        customtheme();

        date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        Log.d("datess", date + "");
        String [] dateParts = date.split("-");
        String day = dateParts[0];
        String month = dateParts[1];
        currentyear = dateParts[2];
        Log.d("currentyear",currentyear);



        value = getIntent().getStringExtra("Setting");
        String anotherId = getIntent().getStringExtra("anotherId");
        personalProfilePresenter = new PersonalProfilePresenter(activity, this, h_recyclerview);
        personalProfilePresenter.personalprofileData(anotherId);
        ArrayList<InterestModelClass> arrayList = new ArrayList<>();
        arrayList.add(new InterestModelClass("Travel"));
        arrayList.add(new InterestModelClass("Dance"));
        arrayList.add(new InterestModelClass("Music"));
        arrayList.add(new InterestModelClass("Music"));
        arrayList.add(new InterestModelClass("Music"));
        arrayList.add(new InterestModelClass("Music"));
        arrayList.add(new InterestModelClass("Music"));
        interestRecyclerAdapter = new InterestRecyclerAdapter(activity, arrayList);
        recyclerview_interest.setHasFixedSize(true);
        recyclerview_interest.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        recyclerview_interest.setAdapter(interestRecyclerAdapter);

        viewPager_adapter = new PprofileViewPager_Adapter(activity, images);
        viewpager.setAdapter(viewPager_adapter);
        indicator.setViewPager(viewpager);


    }

    private void init() {
        h_recyclerview = findViewById(R.id.h_recyclerview);
        viewpager = findViewById(R.id.viewpager);
        indicator = findViewById(R.id.indicator);
        img_back = findViewById(R.id.img_back);
        relativelayout = findViewById(R.id.relativelayout);
        lin_fav = findViewById(R.id.lin_fav);
        recyclerview_interest = findViewById(R.id.recyclerview_interest);
        lin_exit = findViewById(R.id.lin_exit);
        tv_username = findViewById(R.id.tv_username);
        tv_userage = findViewById(R.id.tv_userage);
        tv_description = findViewById(R.id.tv_description);
        tv_aboutmedetail = findViewById(R.id.tv_aboutmedetail);
        img_userimage = findViewById(R.id.img_userimage);
        lin_trends = findViewById(R.id.lin_trends);
        img_onlinestatus = findViewById(R.id.img_onlinestatus);

    }

    private void listeners() {
        img_back.setOnClickListener(this);
        lin_fav.setOnClickListener(this);
        lin_exit.setOnClickListener(this);
        lin_trends.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == img_back) {
//            Intent intent= new Intent(activity,HomeActivity.class);
//            intent.putExtra("Setting","perprofile");
//            startActivity(intent);


            if (value.equalsIgnoreCase("trends")) {
                Intent intent1 = new Intent(activity, HomeActivity.class);
                intent1.putExtra("Setting", "trends");
                startActivity(intent1);
            } else if (value.equalsIgnoreCase("fav")) {
                Intent intent12 = new Intent(activity, HomeActivity.class);
                intent12.putExtra("Setting", "fav");
                startActivity(intent12);

            } else if (value.equalsIgnoreCase("req")) {
                Intent intent12 = new Intent(activity, HomeActivity.class);
                intent12.putExtra("Setting", "req");
                startActivity(intent12);
            }


        } else if (v == lin_fav) {
            Intent intent = new Intent(activity, ConfirmRoleActivity.class);
            startActivity(intent);

        } else if (v == lin_exit) {
            if (value.equalsIgnoreCase("trends")) {
                Intent intent1 = new Intent(activity, HomeActivity.class);
                intent1.putExtra("Setting", "trends");
                startActivity(intent1);
            } else if (value.equalsIgnoreCase("fav")) {
                Intent intent12 = new Intent(activity, HomeActivity.class);
                intent12.putExtra("Setting", "fav");
                startActivity(intent12);

            } else if (value.equalsIgnoreCase("req")) {
                Intent intent12 = new Intent(activity, HomeActivity.class);
                intent12.putExtra("Setting", "req");
                startActivity(intent12);
            }
        } else if (v == lin_trends) {
            Toast.makeText(activity, "req", Toast.LENGTH_SHORT).show();
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
            relativelayout.setBackgroundColor(getResources().getColor(R.color.maleblueTheme));
//            SelectGenderActivity.indicatorSeekBar.setBackgroundColor(getResources().getColor(R.color.maleblueTheme));
            Utility.onActivityCreateSetTheme(this);

        } else {
            setTheme(R.style.Femaletheme);
            relativelayout.setBackgroundColor(getResources().getColor(R.color.femalepinkTheme));
        }

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
        if (data.getDob().isEmpty()){
            tv_userage.setText("");


        }else{
            String apidate = data.getDob();
            Log.d("apidate", apidate + "");
//        String finaldate = date-apidate;
            String [] dateParts = apidate.split("-");
            String day = dateParts[0];
            String month = dateParts[1];
            String userdobyear = dateParts[2];
//        Log.d("year",year);
            int intyear = Integer.parseInt(currentyear);
            int intyear1 = Integer.parseInt(userdobyear);
            int age = intyear - intyear1;
//       Log.d("finaldate",age+"");
            String userage = String.valueOf(age);
            Log.d("finalage",userage);
            tv_userage.setText(userage);

        }
        if(data.getStatus().equalsIgnoreCase("active")){
            img_onlinestatus.setColorFilter(Color.parseColor("#008000"));
        }else{
            img_onlinestatus.setColorFilter(Color.parseColor("#FF0000"));
        }

        tv_description.setText(data.getDescription());
        tv_aboutmedetail.setText(data.getAboutMe());

        if (data.getProfileImageName().isEmpty()) {
            img_userimage.setImageResource(R.drawable.userdummy);
        } else {
            Picasso.with(activity).load(data.getProfileImageName())
                    .into(img_userimage);
        }

    }

    private String getAge(int day, int month, int year) {
        Calendar dob1 = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        Log.d("dateofb", dob1 + "");
        Log.d("currentdate", today + "");
//        data.getDob();

        dob1.set(year, month, day);

//        int age = today.get(Calendar.YEAR) - foo;
////
////        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
////            age--;
////        }
//
//        Integer ageInt = new Integer(age);
//        calculateAge = ageInt.toString();

        return calculateAge;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

//        Intent intent= new Intent(activity,HomeActivity.class);
//        intent.putExtra("Setting","trends");
//        startActivity(intent);

        if (value.equalsIgnoreCase("trends")) {
            Intent intent1 = new Intent(activity, HomeActivity.class);
            intent1.putExtra("Setting", "trends");
            startActivity(intent1);
        } else if (value.equalsIgnoreCase("fav")) {
            Intent intent12 = new Intent(activity, HomeActivity.class);
            intent12.putExtra("Setting", "fav");
            startActivity(intent12);

        } else if (value.equalsIgnoreCase("req")) {
            Intent intent121 = new Intent(activity, HomeActivity.class);
            intent121.putExtra("Setting", "req");
            startActivity(intent121);
        }


    }
}