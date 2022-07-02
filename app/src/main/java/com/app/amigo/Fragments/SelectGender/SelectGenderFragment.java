package com.app.amigo.Fragments.SelectGender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.amigo.Fragments.Additionalinformation.AdditionalinformationFragment;
import com.app.amigo.Fragments.Personalinformation.PersonalinformationFragment;
import com.app.amigo.R;
import com.app.amigo.Utility;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;


public class SelectGenderFragment extends Fragment implements View.OnClickListener {
    private Activity activity;
    private View view;
    private Button btn_male;
    private Button btn_feMale;
    private String values;
//    private String value;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_select_gender, container, false);
        activity = getActivity();
        init();
        listeners();
//        Bundle bundle = activity.getIntent().getExtras();
//        if (bundle != null) {
//            value = bundle.getString("selgenderfragment");
//        }



//        Bundle bundle = activity.getIntent().getExtras();
//        if (bundle != null) {
//            values = bundle.getString("selgender");
//        }

        return view;
    }

    private void init() {
        btn_male = view.findViewById(R.id.btn_male);
        btn_feMale = view.findViewById(R.id.btn_feMale);
    }

    private void listeners() {
        btn_male.setOnClickListener(this);
        btn_feMale.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_male:
                CSPreferences.putString(activity, Utils.GENDERSELECT, "Male");
                Utility.changeToTheme(activity, Utility.THEME_DEFAULT);
//                Utils.changeFragment(activity, new PersonalinformationFragment());


                PersonalinformationFragment fragment = new PersonalinformationFragment();
                Bundle bundle = new Bundle();
                bundle.putString("gender", "male");
//                bundle.putString("selgender", values);
                fragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.selGender_container, fragment);
                fragmentTransaction.commit();

//                Bundle args = new Bundle();
//                PersonalinformationFragment personalinformationFragment = new PersonalinformationFragment();
//                args.putString("gender", "male");
//                personalinformationFragment.setArguments(args);
//                getFragmentManager().beginTransaction().add(R.id.selGender_container, personalinformationFragment).commit();

                break;

            case R.id.btn_feMale:
                CSPreferences.putString(activity, Utils.GENDERSELECT, "Female");
//                Utils.changeFragment(activity, new PersonalinformationFragment());


                PersonalinformationFragment fragment2 = new PersonalinformationFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("gender", "female");
//                bundle2.putString("selgender", values);
                fragment2.setArguments(bundle2);
                FragmentTransaction fragmentTransaction2 = getParentFragmentManager().beginTransaction();
                fragmentTransaction2.add(R.id.selGender_container, fragment2);
                fragmentTransaction2.commit();

//                Bundle args1 = new Bundle();
//                PersonalinformationFragment personalinformationFragment1 = new PersonalinformationFragment();
//
//                args1.putString("gender", "female");
//                personalinformationFragment1.setArguments(args1);
//                getFragmentManager().beginTransaction().add(R.id.selGender_container, personalinformationFragment1).commit();


                break;
        }

    }

    private void recreateActivity() {
        Intent intent = activity.getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        Utils.changeFragment(activity, new PersonalinformationFragment());


        startActivity(intent);
        activity.finish();
    }
}