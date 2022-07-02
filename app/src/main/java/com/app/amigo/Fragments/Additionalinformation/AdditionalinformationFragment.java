package com.app.amigo.Fragments.Additionalinformation;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.amigo.Activities.SelectGender.SelectGenderActivity;
import com.app.amigo.Fragments.Additionalinformation.presenter.AditionalInformationPresenter;
import com.app.amigo.Fragments.Additionalinformation.presenter.UploadGalleryImagesPresenter;
import com.app.amigo.Fragments.Additionalinformation.view.AditionalInformationView;
import com.app.amigo.Fragments.EnableLocation.EnableLocationFragment;
import com.app.amigo.Fragments.Personalinformation.PersonalinformationFragment;
import com.app.amigo.Fragments.Personalinformation.View.PersonalinformatioView;
import com.app.amigo.Models.PersonalProfile.GetCurrentProfileData.PersonalProfileData;
import com.app.amigo.R;
import com.app.amigo.Utility;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class AdditionalinformationFragment extends Fragment implements View.OnClickListener, AditionalInformationView {
    private static final int SELECT_PICTURE = 1;
    private View view;
    private Activity activity;
    private GridView grid_builder;
    private AditionalInformationPresenter aditionalInformationPresenter;
    private Button btn_continue;
    private ImageView img_back;
    private String gender;
    private LinearLayout linearlayout;
    private EditText et_aboutyou;
    private EditText et_currentjob;
    private EditText et_education;
    private EditText et_city;
    private EditText et_height;
    private EditText et_weight;
    private EditText et_favsports;
    private EditText et_lookingfor;
    private EditText et_lasteducation;
    String name;
    String email;
    String phonenum;
    String dob;
    String lookingfor;
    private ImageView img_add;
    private Bitmap bitmap;
    private UploadGalleryImagesPresenter uploadGalleryImagesPresenter;
    private ArrayList<String> images = new ArrayList<>();
    private String selSex;
    ArrayList<Uri> mArrayUri;
    private PersonalProfileData data;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        changetheme();
        view = inflater.inflate(R.layout.fragment_additionalinformation, container, false);
        activity = getActivity();
        init();
        listners();
        customtheme();
        mArrayUri = new ArrayList<Uri>();
        name = getArguments().getString("name");
        email = getArguments().getString("email");
        phonenum = getArguments().getString("phonenum");
        dob = getArguments().getString("dob");
        lookingfor = getArguments().getString("lookingfor");
        selSex = getArguments().getString("gender");
        Log.i("names", name);
        Log.i("email", email);

//        SelectGenderActivity.indicatorSeekBar.setProgress(75);
        aditionalInformationPresenter = new AditionalInformationPresenter(this, activity, grid_builder);
        uploadGalleryImagesPresenter = new UploadGalleryImagesPresenter(activity, this);
        aditionalInformationPresenter.personalprofileData();
        return view;
    }


    private void init() {
        grid_builder = view.findViewById(R.id.grid_builder);
        btn_continue = view.findViewById(R.id.btn_continue);
        img_back = view.findViewById(R.id.img_back);
        linearlayout = view.findViewById(R.id.linearlayout);
        et_aboutyou = view.findViewById(R.id.et_aboutyou);
        et_currentjob = view.findViewById(R.id.et_currentjob);
        et_education = view.findViewById(R.id.et_education);
        et_city = view.findViewById(R.id.et_city);
        et_height = view.findViewById(R.id.et_height);
        et_favsports = view.findViewById(R.id.et_favsports);
        et_lookingfor = view.findViewById(R.id.et_lookingfor);
        et_lasteducation = view.findViewById(R.id.et_lasteducation);
        et_weight = view.findViewById(R.id.et_weight);
    }

    private void listners() {
        btn_continue.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_continue) {
            if (checkValidation()) {
//                if (images.size() >= 6) {
////                    uploadGalleryImagesPresenter.UploadGalleryIMages(mArrayUri);
//                }

                EnableLocationFragment enableLocationFragment = new EnableLocationFragment();
                Bundle args = new Bundle();
                args.putString("name", name);
                args.putString("email", email);
                args.putString("phonenum", phonenum);
                args.putString("dob", dob);
                args.putString("lookingfor", et_lookingfor.getText().toString());
                args.putString("et_aboutyou", et_aboutyou.getText().toString());
                args.putString("et_currentjob", et_currentjob.getText().toString());
                args.putString("et_education", et_education.getText().toString());
                args.putString("et_city", et_city.getText().toString());
                args.putString("et_height", et_height.getText().toString());
                args.putString("et_weight", et_weight.getText().toString());
                args.putString("et_favsports", et_favsports.getText().toString());
                args.putString("et_lookingfor", et_lookingfor.getText().toString());
                args.putString("et_lasteducation", et_lasteducation.getText().toString());
                args.putString("gender", selSex);
                enableLocationFragment.setArguments(args);
                getFragmentManager().beginTransaction().add(R.id.selGender_container, enableLocationFragment).commit();

            }

//            aditionalInformationPresenter.userlist(name, email, phonenum, dob, lookingfor,
//                    et_aboutyou, et_currentjob, et_education, et_city,
//                    et_height, et_weight, et_favsports, et_lookingfor, et_lasteducation);
//            Utils.changeFragment(activity, new EnableLocationFragment());

        } else if (v == img_back) {
            Utils.changeFragment(activity, new PersonalinformationFragment());
        }
    }

    private boolean checkValidation() {
        if (et_aboutyou.getText().toString().length() == 0) {
            Toast.makeText(activity, "Please enter short things about you", Toast.LENGTH_SHORT).show();
            return false;
        } else if (et_currentjob.getText().toString().length() == 0) {
            Toast.makeText(activity, "Please enter your current job", Toast.LENGTH_SHORT).show();
            return false;
        } else if (et_lasteducation.getText().toString().length() == 0) {
            Toast.makeText(activity, "Please enter your last education", Toast.LENGTH_SHORT).show();
            return false;
        } else if (et_city.getText().toString().length() == 0) {
            Toast.makeText(activity, "Please enter your current city", Toast.LENGTH_SHORT).show();
            return false;
        } else if (et_height.getText().toString().length() == 0) {
            Toast.makeText(activity, "Please enter your height ", Toast.LENGTH_SHORT).show();
            return false;
        } else if (et_weight.getText().toString().length() == 0) {
            Toast.makeText(activity, "Please enter your weight ", Toast.LENGTH_SHORT).show();
            return false;
        } else if (et_favsports.getText().toString().length() == 0) {
            Toast.makeText(activity, "Please enter favorite sports ", Toast.LENGTH_SHORT).show();
            return false;
        } else if (et_lookingfor.getText().toString().length() == 0) {
            Toast.makeText(activity, "Please what are you looking for", Toast.LENGTH_SHORT).show();
            return false;
        } else if (et_lasteducation.getText().toString().length() == 0) {
            Toast.makeText(activity, "Please what are you looking for", Toast.LENGTH_SHORT).show();
            return false;

        }

        return true;
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
    public void selectImage(ImageView img_add) {

        this.img_add = img_add;


        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(galleryIntent, SELECT_PICTURE);

//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        intent.setType("image/*");
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @Override
    public void setData(PersonalProfileData data) {
        this.data = data;
        et_aboutyou.setText(data.getDescription());
        et_currentjob.setText(data.getMyWork());
        et_education.setText(data.getSchool());
        et_city.setText(data.getLivingIn());
        et_height.setText(data.getHeight());
        et_weight.setText(data.getWeight());
        et_favsports.setText(data.getFavSports());
        et_lookingfor.setText(data.getLookingFor());
        et_lasteducation.setText(data.getDegreeOfEduction());
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
            btn_continue.setBackgroundColor(getResources().getColor(R.color.malebuttoncolor));
            SelectGenderActivity.indicatorSeekBar.setBackgroundColor(getResources().getColor(R.color.maleblueTheme));
            Utility.onActivityCreateSetTheme(getActivity());
        } else {
            activity.setTheme(R.style.Femaletheme);
            linearlayout.setBackgroundColor(getResources().getColor(R.color.femalepinkTheme));
        }

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    String path = getPathFromURI(selectedImageUri);
//                    Log.d(TAG, "onActivityimg" + path);
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), selectedImageUri);
                        uploadGalleryImagesPresenter.UploadGalleryIMages(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    img_add.setImageURI(selectedImageUri);
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


    private void uploadMultiFile() {


        ArrayList<String> filePaths = new ArrayList<>();
        filePaths.add("storage/emulated/0/DCIM/Camera/IMG_20170802_111432.jpg");
        filePaths.add("storage/emulated/0/Pictures/WeLoveChat/587c4178e4b0060e66732576_294204376.jpg");
        filePaths.add("storage/emulated/0/Pictures/WeLoveChat/594a2ea4e4b0d6df9153028d_265511791.jpg");

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        builder.addFormDataPart("user_name", "Robert");
        builder.addFormDataPart("email", "mobile.apps.pro.vn@gmail.com");

        // Map is used to multipart the file using okhttp3.RequestBody
        // Multiple Images
        for (int i = 0; i < filePaths.size(); i++) {
            File file = new File(filePaths.get(i));
            builder.addFormDataPart("file[]", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
        }
    }


}