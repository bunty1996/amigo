package com.app.amigo.Fragments.EnableLocation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.amigo.Activities.SelectGender.SelectGenderActivity;
import com.app.amigo.Fragments.Additionalinformation.presenter.AditionalInformationPresenter;
import com.app.amigo.Fragments.EnableLocation.View.EnableLocationView;
import com.app.amigo.R;
import com.app.amigo.Utility;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EnableLocationFragment extends Fragment implements View.OnClickListener, EnableLocationView {
    private Activity activity;
    private View view;
    Button btn_allowLocation;
    private LinearLayout linearlayout;
    private String gender;
    FusedLocationProviderClient mFusedLocationClient;
    int PERMISSION_ID = 44;
    private AditionalInformationPresenter aditionalInformationPresenter;
    private String name;
    private String email;
    private String phonenum;
    private String dob;
    private String lookingfor;
    private String et_aboutyou;
    private String et_currentjob;
    private String et_education;
    private String et_city;
    private String et_height;
    private String et_weight;
    private String et_favsports;
    private String et_lookingfor;
    private String et_lasteducation;
    private String selSex;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> a;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        changetheme();
        view = inflater.inflate(R.layout.fragment_enable_location, container, false);
        activity = getActivity();
        init();
        listeners();
        customtheme();

        name = getArguments().getString("name");
        email = getArguments().getString("email");
        phonenum = getArguments().getString("phonenum");
        dob = getArguments().getString("dob");
        lookingfor = getArguments().getString("lookingfor");
        et_aboutyou = getArguments().getString("et_aboutyou");
        et_currentjob = getArguments().getString("et_currentjob");
        et_education = getArguments().getString("et_education");
        et_city = getArguments().getString("et_city");
        et_height = getArguments().getString("et_height");
        et_weight = getArguments().getString("et_weight");
        et_favsports = getArguments().getString("et_favsports");
        et_lookingfor = getArguments().getString("et_lookingfor");
        et_lasteducation = getArguments().getString("et_lasteducation");
        selSex = getArguments().getString("gender");
//        SelectGenderActivity.indicatorSeekBar.setProgress(100);
        aditionalInformationPresenter = new AditionalInformationPresenter(this, activity);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);

        // method to get the location
        getLastLocation();
        return view;
    }


    private void init() {
        btn_allowLocation = view.findViewById(R.id.btn_allowLocation);
        linearlayout = view.findViewById(R.id.linearlayout);
    }

    private void listeners() {
        btn_allowLocation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_allowLocation) {
            aditionalInformationPresenter.userlist(name, email, phonenum, dob, lookingfor,
                    et_aboutyou, et_currentjob, a, et_education, et_city,
                    et_height, et_weight, et_favsports, et_lookingfor, et_lasteducation, selSex);
//            Intent i = new Intent(getActivity(), HomeActivity.class);
//            startActivity(i);
//            ((Activity) getActivity()).overridePendingTransition(0, 0);

        }
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

    private void customtheme() {
        if (gender.equalsIgnoreCase("Male")) {
            activity.setTheme(R.style.Maletheme);
            linearlayout.setBackgroundColor(getResources().getColor(R.color.maleblueTheme));
            btn_allowLocation.setBackgroundColor(getResources().getColor(R.color.malebuttoncolor));
            SelectGenderActivity.indicatorSeekBar.setBackgroundColor(getResources().getColor(R.color.maleblueTheme));
            Utility.onActivityCreateSetTheme(getActivity());
        } else {
            activity.setTheme(R.style.Femaletheme);
            linearlayout.setBackgroundColor(getResources().getColor(R.color.femalepinkTheme));
        }

    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        // check if permissions are given
        if (checkPermissions()) {

            // check if location is enabled
            if (isLocationEnabled()) {

                // getting last
                // location from
                // FusedLocationClient
                // object
                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                            double lat = location.getLatitude();
                            double longi = location.getLongitude();

                            Log.e("liter", location.getLatitude() + "");

                        } else {
                            Log.e("long123", location.getLatitude() + "");
                            Log.e("long123", location.getLongitude() + "");

                            a = new ArrayList<>();
                            a.add(location.getLatitude() + "");
                            a.add(location.getLongitude() + "");

                            for (int i = 0; i < a.size(); i++) {

                                Log.e("NOVEX", a.size() + "");
                                Log.e("NOVEXSS", a.get(0) + "");
                                Log.e("NOVEXIII", a.get(1) + "");
                            }


//
//                            list.add(location.getLatitude()+"");
//                            list.add(location.getLongitude()+"");
//                            Log.e("hello",list+"");

//                            latitudeTextView.setText(location.getLatitude() + "");
//                            longitTextView.setText(location.getLongitude() + "");
                        }
                    }
                });
            } else {
                Toast.makeText(activity, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            // if permissions aren't available,
            // request for permissions
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        // setting LocationRequest
        // on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();

            Log.e("Latitude", mLastLocation.getLatitude() + "");
            Log.e("Longitude", mLastLocation.getLongitude() + "");
//            latitudeTextView.setText("Latitude: " + mLastLocation.getLatitude() + "");
//            longitTextView.setText("Longitude: " + mLastLocation.getLongitude() + "");
        }
    };

    // method to check for permissions
    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        // If we want background location
        // on Android 10.0 and higher,
        // use:
        // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    // method to request for permissions
    private void requestPermissions() {
        ActivityCompat.requestPermissions(activity, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    // method to check
    // if location is enabled
    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    // If everything is alright then
    @Override
    public void
    onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
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
}