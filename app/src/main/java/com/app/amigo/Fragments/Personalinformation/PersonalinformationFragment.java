package com.app.amigo.Fragments.Personalinformation;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.amigo.Activities.SelectGender.SelectGenderActivity;
import com.app.amigo.Activities.Setting.SettingActivity;
import com.app.amigo.Fragments.Additionalinformation.AdditionalinformationFragment;
import com.app.amigo.Fragments.Personalinformation.Presenter.PersonalinformatioPresenter;
import com.app.amigo.Fragments.Personalinformation.View.PersonalinformatioView;
import com.app.amigo.Fragments.SelectGender.SelectGenderFragment;
import com.app.amigo.Models.PersonalProfile.GetCurrentProfileData.PersonalProfileData;
import com.app.amigo.R;
import com.app.amigo.Utility;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;

import java.util.Calendar;

public class PersonalinformationFragment extends Fragment implements View.OnClickListener, PersonalinformatioView {
    private View view;
    private Activity activity;
    private Button btn_continue;
    private ImageView img_back;
    private LinearLayout linearlayout;
    private String gender;
    PersonalinformationFragment fragment;
    private EditText et_Name;
    private EditText et_email;
    private EditText et_PhoneNumber;

    private EditText et_lookingfor;
    private TextView tv_dob;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private String selSex;
    private String getvalue;
    private String getvalue2;
    private PersonalinformatioPresenter personalinformatioPresenter;
    private PersonalProfileData data;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        changetheme();
        view = inflater.inflate(R.layout.fragment_personalinformation, container, false);
        activity = getActivity();
//        SelectGenderActivity.indicatorSeekBar.setProgress(50);
//        indicatorSeekBar.tickMarksColor(getResources().getColor(R.color.femalepinkTheme));
        init();
        listeners();


        personalinformatioPresenter = new PersonalinformatioPresenter(activity, this);
        personalinformatioPresenter.personalprofileData();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            customtheme();
        }

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            selSex = bundle.getString("gender");
        }
//        Log.e("Gender",selSex);

        Bundle bundle1 = this.getArguments();
        if (bundle != null) {
            getvalue = bundle1.getString("setting");
        }
        Bundle bundle2 = this.getArguments();
        if (bundle != null) {
            getvalue2 = bundle2.getString("selgender");
        }


        return view;
    }

    private void init() {
        btn_continue = view.findViewById(R.id.btn_continue);
        img_back = view.findViewById(R.id.img_back);
        linearlayout = view.findViewById(R.id.linearlayout);
        et_Name = view.findViewById(R.id.et_Name);
        et_email = view.findViewById(R.id.et_email);
        et_PhoneNumber = view.findViewById(R.id.et_PhoneNumber);
        et_lookingfor = view.findViewById(R.id.et_lookingfor);
        et_lookingfor = view.findViewById(R.id.et_lookingfor);
        tv_dob = view.findViewById(R.id.tv_dob);
    }


    private void listeners() {
        btn_continue.setOnClickListener(this);
        img_back.setOnClickListener(this);
        tv_dob.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_continue) {
            if (checkValidation()) {


//            Utils.changeFragment(activity,new AdditionalinformationFragment());
                AdditionalinformationFragment additionalinformationFragment = new AdditionalinformationFragment();
                Bundle args = new Bundle();
                args.putString("name", et_Name.getText().toString());
                args.putString("email", et_email.getText().toString());
                args.putString("phonenum", et_PhoneNumber.getText().toString());
                args.putString("dob", tv_dob.getText().toString());
                args.putString("lookingfor", et_lookingfor.getText().toString());
                args.putString("gender", selSex);
                additionalinformationFragment.setArguments(args);
                getFragmentManager().beginTransaction().add(R.id.selGender_container, additionalinformationFragment).commit();
            }
        } else if (v == img_back) {
            if (getvalue != null && getvalue.equalsIgnoreCase("editprofile")) {

//                if (called_from != null && called_from.equalsIgnoreCase("add")) {

                Intent intent = new Intent(activity, SettingActivity.class);
                startActivity(intent);

            } else {
//                Utils.changeFragment(activity,new SelectGenderFragment());
                Intent intent = new Intent(activity, SelectGenderActivity.class);
                startActivity(intent);

            }


        } else if (v == tv_dob) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(activity,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            tv_dob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

        }

    }

    private boolean checkValidation() {
        if (et_Name.getText().toString().trim().length() == 0) {
            Toast.makeText(activity, "Please enter name", Toast.LENGTH_SHORT).show();
            return false;

        } else if (et_PhoneNumber.getText().toString().trim().length() == 0) {
            Toast.makeText(activity, "Please enter phone number", Toast.LENGTH_SHORT).show();
            return false;
        } else if (tv_dob.getText().toString().length() == 0) {
            Toast.makeText(activity, "Please select date of birth", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    private void changetheme() {
        gender = CSPreferences.readString(getActivity(), Utils.GENDERSELECT);
        if (gender.equalsIgnoreCase("Male")) {
            getActivity().setTheme(R.style.Maletheme);
            Utility.onActivityCreateSetTheme(getActivity());
        } else {
            getActivity().setTheme(R.style.Femaletheme);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void customtheme() {
        if (gender.equalsIgnoreCase("Male")) {
            activity.setTheme(R.style.Maletheme);
            linearlayout.setBackgroundColor(getResources().getColor(R.color.maleblueTheme));
            btn_continue.setBackgroundColor(getResources().getColor(R.color.malebuttoncolor));
//            SelectGenderActivity.indicatorSeekBar.setBackgroundColor(getResources().getColor(R.color.maleblueTheme));
            Utility.onActivityCreateSetTheme(getActivity());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.maleblueTheme));
            }

        } else {
            activity.setTheme(R.style.Femaletheme);
            linearlayout.setBackgroundColor(getResources().getColor(R.color.femalepinkTheme));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.femalepinkTheme));
            }
//            SelectGenderActivity.indicatorSeekBar.setBackgroundColor(getResources().getColor(R.color.femalepinkTheme));


        }

    }

    @Override
    public void showDialog(Activity activity) {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void showMessage(Activity activity, String msg) {

    }

    @Override
    public void setData(PersonalProfileData data) {

        this.data = data;
        et_Name.setText(data.getName());
        et_PhoneNumber.setText(data.getPhoneNo());
        tv_dob.setText(data.getDob());
        et_lookingfor.setText(data.getLookingFor());
//        Log.d("namesa",data.getName());
    }


}