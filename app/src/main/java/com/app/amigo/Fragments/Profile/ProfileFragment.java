package com.app.amigo.Fragments.Profile;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.amigo.Activities.Setting.SettingActivity;
import com.app.amigo.Fragments.Profile.Presenter.ProfilePresenter;
import com.app.amigo.Fragments.Profile.View.ProfileView;
import com.app.amigo.Models.PersonalProfile.GetCurrentProfileData.PersonalProfileData;
import com.app.amigo.R;
import com.app.amigo.Utility;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;
import com.bumptech.glide.Glide;

import java.io.IOException;


public class ProfileFragment extends Fragment implements View.OnClickListener, ProfileView {
    private Activity activity;
    private View view;
    private LinearLayout linearlayout;
    private String gender;
    private ImageView img_setting;
    private TextView tv_username;
    private TextView et_fullname;
    private TextView tv_aboutme;
    private TextView et_email;
    private ProfilePresenter profilePresenter;
    private ImageView img_selectimage;
    private ImageView img_setimg;
    private ImageView img_profile;
    private Bitmap bitmap;
    int SELECT_PICTURE = 200;
    final private int PICK_IMAGE = 1;
    final private int CAPTURE_IMAGE = 2;
    private PersonalProfileData personalProfileDataList;

    public static ProfileFragment newInstance() {
        ProfileFragment profileFragment = new ProfileFragment();
        return profileFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        changetheme();
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        activity = getActivity();
        init();
        listeners();
        customtheme();
        profilePresenter = new ProfilePresenter(activity, this);
        profilePresenter.personalprofileData();
        return view;
    }


    private void init() {
        linearlayout = view.findViewById(R.id.linearlayout);
        img_setting = view.findViewById(R.id.img_setting);
        tv_username = view.findViewById(R.id.tv_username);
        et_fullname = view.findViewById(R.id.et_fullname);
        tv_aboutme = view.findViewById(R.id.tv_aboutme);
        et_email = view.findViewById(R.id.et_email);
        img_selectimage = view.findViewById(R.id.img_selectimage);
        img_profile = view.findViewById(R.id.img_profile);
    }

    private void listeners() {
        img_setting.setOnClickListener(this);
        img_selectimage.setOnClickListener(this);
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
//            SelectGenderActivity.indicatorSeekBar.setBackgroundColor(getResources().getColor(R.color.maleblueTheme));
            Utility.onActivityCreateSetTheme(getActivity());
        } else {
            activity.setTheme(R.style.Femaletheme);
            linearlayout.setBackgroundColor(getResources().getColor(R.color.femalepinkTheme));
        }

    }

    @Override
    public void onClick(View v) {
        if (v == img_setting) {
            Intent intent = new Intent(activity, SettingActivity.class);
//            intent.putExtra("name", personalProfileDataList.getName());
//            intent.putExtra("email", personalProfileDataList.getEmail());
//            intent.putExtra("image", personalProfileDataList.getProfileImageName());
            startActivity(intent);
            activity.finish();
        } else if (v == img_selectimage) {
            imageChooser();


        }
    }


    // the Select Image Button is clicked
    private void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);

    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    String path = getPathFromURI(selectedImageUri);
//                    Log.d(TAG, "onActivityimg" + path);
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), selectedImageUri);
                        profilePresenter.uploadprofile(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    img_profile.setImageURI(selectedImageUri);
//                    Log.d(TAG, "onActivityu" + selectedImageUri);
                }
            }
        }
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = activity.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }


//    private void setData() {
//        String username = CSPreferences.readString(activity, Utils.NAME);
//        String email = CSPreferences.readString(activity, Utils.USEREMAIL);
//        String aboutme = CSPreferences.readString(activity, Utils.ABOUTME);
//        tv_username.setText(username);
//        et_fullname.setText(username);
//        et_email.setText(email);
//        tv_aboutme.setText(aboutme);
//
//    }

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

        this.personalProfileDataList = data;
        CSPreferences.putString(activity,Utils.ImageBaseURL,personalProfileDataList.getProfileImageName());


        Glide.with(activity).load(personalProfileDataList.getProfileImageName())
                .placeholder(R.drawable.userdummy).into(img_profile);
        tv_username.setText(personalProfileDataList.getName());
        et_fullname.setText(personalProfileDataList.getName());
        et_email.setText(personalProfileDataList.getEmail());
        tv_aboutme.setText(personalProfileDataList.getDescription());

    }
}