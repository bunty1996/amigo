package com.app.amigo.Fragments.Message;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.amigo.Activities.SelectGender.SelectGenderActivity;
import com.app.amigo.Adapters.MessagesRecylerAdapter;
import com.app.amigo.Adapters.NewMatchesRecyclerAdapter;
import com.app.amigo.Fragments.Message.Presenter.MessagePresenter;
import com.app.amigo.Fragments.Message.View.MessageView;
import com.app.amigo.Models.Message.GetFriendList.GetFriendListDatum;
import com.app.amigo.Models.Message.MatchesModelClass;
import com.app.amigo.Models.Message.MessagesModelClass;
import com.app.amigo.R;
import com.app.amigo.Utility;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class MessageFragment extends Fragment implements MessageView {
    private Activity activity;
    private View view;
    RecyclerView NewMatches_Recyclerview;
    NewMatchesRecyclerAdapter newMatchesRecyclerAdapter;
    RecyclerView Messages_Recyclerview;
    MessagesRecylerAdapter messagesRecylerAdapter;
    private String gender;
    private LinearLayout linearlayout;
    private TextView tv_meassage;
    private TextView tv_newmatches;
    private MessagePresenter messagePresenter;
    private List<Object> items;
    private List<GetFriendListDatum> data;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        changetheme();
        view = inflater.inflate(R.layout.fragment_message, container, false);
        activity = getActivity();
        init();
        listeners();
        customtheme();

        messagePresenter = new MessagePresenter(activity, this, NewMatches_Recyclerview, Messages_Recyclerview);
        messagePresenter.getFriendListMethod();
        messagePresenter.getOldChatListUsers();



        return view;
    }


    private void init() {
        NewMatches_Recyclerview = view.findViewById(R.id.NewMatches_Recyclerview);
        Messages_Recyclerview = view.findViewById(R.id.Messages_Recyclerview);
        linearlayout = view.findViewById(R.id.linearlayout);
        tv_meassage = view.findViewById(R.id.tv_meassage);
        tv_newmatches = view.findViewById(R.id.tv_newmatches);

    }

    private void listeners() {
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
            tv_meassage.setTextColor(getResources().getColor(R.color.darkblue));
            tv_newmatches.setTextColor(getResources().getColor(R.color.darkblue));
            getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.maleblueTheme));

            Utility.onActivityCreateSetTheme(getActivity());
        } else {
            activity.setTheme(R.style.Femaletheme);
            linearlayout.setBackgroundColor(getResources().getColor(R.color.femalepinkTheme));
            getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.femalepinkTheme));

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
    public void setData(List<Object> items) {
        this.items = items;

    }

    @Override
    public void setdatanewmatches(List<GetFriendListDatum> data) {
        this.data = data;
        if (data.isEmpty()) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.emptymeassagescreen, null);
        }else{
            LayoutInflater   inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View rowView = inflater.inflate(R.layout.fragment_message, null);
        }
    }


}