package com.app.amigo.Activities.AditionalInformation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.app.amigo.Activities.EnableLocation.EnableLocationActivity;
import com.app.amigo.Fragments.Additionalinformation.presenter.AditionalInformationPresenter;
import com.app.amigo.R;


public class AditionalInformationActivity extends AppCompatActivity implements View.OnClickListener {

    private Activity activity;
    private GridView grid_builder;
    private AditionalInformationPresenter aditionalInformationPresenter;
    private Button btn_continue;
    private ImageView img_backs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLightStatusBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aditional_information);
        activity = this;
        init();
        listiners();

//        aditionalInformationPresenter = new AditionalInformationPresenter(this, activity, grid_builder);

    }

    private void listiners() {

        btn_continue.setOnClickListener(this);
        img_backs.setOnClickListener(this);

    }

    private void init() {

        grid_builder = findViewById(R.id.grid_builder);
        btn_continue = findViewById(R.id.btn_continue);
        img_backs = findViewById(R.id.img_backs);

    }

    @Override
    public void onClick(View v) {

        if (v == btn_continue) {
            Intent intent = new Intent(activity, EnableLocationActivity.class);
            startActivity(intent);
        }else if (v==img_backs){
            finish();
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