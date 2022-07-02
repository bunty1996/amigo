package com.app.amigo.Fragments.SelectGenderOrientationFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.amigo.Activities.SelectGender.SelectGenderActivity;
import com.app.amigo.Adapters.SelectGenterOrientationAdapter;
import com.app.amigo.R;

import java.util.ArrayList;


public class SelectgenderOrientationFragment extends Fragment implements View.OnClickListener {

    private Activity activity;
    private View view;
    private Button btn_continue;
    private RecyclerView recyclerview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_selectgender_orientation, container, false);
        activity = getActivity();
        init();
        listeners();

        ArrayList<String> list = new ArrayList<String>();

        list.add("Lesbian");
        list.add("Bisexual");
        list.add("Asexual");
        list.add("Demisexual");
        list.add("Queer");
        list.add("Bicurious");
        list.add("Bisexual");
        list.add("Aromantic");

        SelectGenterOrientationAdapter selectGenterOrientationAdapter = new SelectGenterOrientationAdapter( activity,list);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(activity));
        recyclerview.setAdapter(selectGenterOrientationAdapter);

        return view;
    }

    private void listeners() {
        btn_continue.setOnClickListener(this);
    }

    private void init() {
        btn_continue = view.findViewById(R.id.btn_continue);
        recyclerview = view.findViewById(R.id.recyclerview);
    }

    @Override
    public void onClick(View v) {

        if (v == btn_continue){

            Intent intent = new Intent(activity, SelectGenderActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }
    }
}