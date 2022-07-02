package com.app.amigo.Activities.ChatDetail;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.amigo.Activities.ChatDetail.Presenter.ChatDetailPresenter;
import com.app.amigo.Activities.ChatDetail.View.ChatDetailView;
import com.app.amigo.Adapters.ChatAdapterRecyclerview;
import com.app.amigo.Adapters.MessagesRecylerAdapter;
import com.app.amigo.R;
import com.app.amigo.Utility;
import com.app.amigo.Utils.CSPreferences;
import com.app.amigo.Utils.ChatApplication.Messages;
import com.app.amigo.Utils.Utils;


import com.app.humanresource.Utils.ChatApplication.ChatApplication;
import com.github.nkzawa.emitter.Emitter;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;


public class ChatDetailActivity extends AppCompatActivity implements View.OnClickListener, ChatDetailView {
    private Activity activity;
    private ImageButton img_back;
    private TextView txt_name;
    private ImageView img_userimage;
    private String gender;
    private LinearLayout linearlayout;
    private LinearLayout linearlayout2;
    private RecyclerView chatrecyclerview;
    private EditText et_msg;
    private ImageButton btnSend;
    private ChatAdapterRecyclerview chatAdapterRecyclerview;
    private String imgUrl;
    private String username;
    private String message;
    private String userId;
    private String toUser;
    private List<Messages> messageList;
    private Socket mSocket;
    private ChatDetailPresenter chatDetailPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messageList = new ArrayList<>();
        ChatApplication app = new ChatApplication();

        //mSocket = app.getmSocket();
//        helper = PreferenceHelper.getInstance(getActivity());
        // roomId = helper.getroomid();
        mSocket = app.getmSocket();
//        mSocket.connect();

        mSocket.on(Socket.EVENT_CONNECT, onConnect);
        mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectionError);
        //  mSocket.on("set-user-data",)
        mSocket.on("set-room", onGettingRoomId);
        mSocket.on("chat-msg", onNewMessage);
        //mSocket.on("typing", onTyping);
        mSocket.connect();
        Log.d(TAG, "socket" + mSocket.connected());
        mSocket = app.getmSocket();

        changetheme();
        setContentView(R.layout.activity_chat_detail);
        activity = this;
        init();
        listeners();
        customtheme();
        chatDetailPresenter = new ChatDetailPresenter(activity,this);
        chatDetailPresenter.getOldchatList(toUser);


//        String name = getIntent().getStringExtra("name");
//        txt_name.setText(name);
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            int resId = bundle.getInt("URL");
//            img_userimage.setImageResource(resId);
//        }


        username = getIntent().getStringExtra("name");
        imgUrl = getIntent().getStringExtra("URL");
        toUser = getIntent().getStringExtra("id");
//        Log.d("useriding",toUser);


        if (imgUrl.isEmpty()) {
            img_userimage.setImageResource(R.drawable.userimage);
//            Picasso.with(activity).load(imgUrl).placeholder(R.drawable.userdummy).into(img_userimage);
        } else {
            Picasso.with(activity).load(imgUrl).placeholder(R.drawable.userdummy).into(img_userimage);

        }
        txt_name.setText(username);

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        chatrecyclerview.setLayoutManager(layoutManager);
        chatrecyclerview.setAdapter(chatAdapterRecyclerview);


    }


    private void init() {
        img_back = findViewById(R.id.img_back);
        txt_name = findViewById(R.id.txt_name);
        img_userimage = findViewById(R.id.img_userimage);
        linearlayout = findViewById(R.id.linearlayout);
        linearlayout2 = findViewById(R.id.linearlayout2);
        chatrecyclerview = findViewById(R.id.chatrecyclerview);
        et_msg = findViewById(R.id.et_msg);
        btnSend = findViewById(R.id.btnSend);
        chatAdapterRecyclerview = new ChatAdapterRecyclerview(messageList);

    }

    private void listeners() {
        img_back.setOnClickListener(this);
        btnSend.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == img_back) {
            finish();
        } else if (v == btnSend) {
            attemptSend();
            chatrecyclerview.post(new Runnable() {
                @Override
                public void run() {
                    chatrecyclerview.scrollToPosition(chatAdapterRecyclerview.getItemCount() - 1);
                    // Here adapter.getItemCount()== child count
                }
            });

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
            linearlayout2.setBackgroundColor(getResources().getColor(R.color.maleblueTheme));
//            SelectGenderActivity.indicatorSeekBar.setBackgroundColor(getResources().getColor(R.color.maleblueTheme));
            Utility.onActivityCreateSetTheme(this);
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(ContextCompat.getColor(activity, R.color.maleblueTheme));
            }

        } else {
            setTheme(R.style.Femaletheme);
            linearlayout.setBackgroundColor(getResources().getColor(R.color.femalepinkTheme));
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(ContextCompat.getColor(activity, R.color.lightpinkcolor));
            }

        }

    }


    private void attemptSend() {
        if (!mSocket.connected()) return;
        String message = et_msg.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            et_msg.requestFocus();
            return;
        }
        et_msg.setText("");

        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat mdformat = new SimpleDateFormat("dd/MM/yyyy hh.mm aa");
        String strDate = mdformat.format(calendar.getTime());
        Log.d(TAG, "attemptSenddate" + strDate);

        // String date = "2021-02-16T15:24:44.000Z";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("msg", message);
            jsonObject.put("sender", toUser);
            jsonObject.put("date", strDate);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, jsonObject.toString() + "   emit this");
        Log.d(TAG, "socket" + mSocket.connected());
        addMessage(strDate, message, Messages.TYPE_MESSAGE);
        mSocket.emit("chat-msg", jsonObject);


    }

    public Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d(TAG, "Socket Connected!" + mSocket.connected());
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, "connected", Toast.LENGTH_SHORT).show();
//                    if (!isconnected) {
//                        Snackbar s = Snackbar.make(binding.getRoot(), "Loading..", Snackbar.LENGTH_LONG);
//                        s.getView().setBackgroundColor(binding.getRoot().getResources().getColor(R.color.orange));
//                        isconnected = true;
//                        s.show();
//                    }
                }
            });
        }
    };

    private Emitter.Listener onConnectionError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity, "Connection error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    private Emitter.Listener onGettingRoomId = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //  roomId = args[0].toString();
//                    if (!roomId.isEmpty()) {
//                        //  getOldChats(roomId);
//                    }
                }
            });

        }
    };


    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (activity != null) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        chatrecyclerview.post(new Runnable() {
                            @Override
                            public void run() {
                                chatrecyclerview.scrollToPosition(chatAdapterRecyclerview.getItemCount() - 1);
                                // Here adapter.getItemCount()== child count
                            }
                        });

                        JSONObject jsonObject = (JSONObject) args[0];
                        if (jsonObject.has("msgFrom") && jsonObject.optString("msgFrom").equalsIgnoreCase(toUser)) {

                            String time;
                            String message;
                            try {

                                time = jsonObject.getString("date");
                                message = jsonObject.getString("msg");
                                Log.d(TAG, message + "   received msg");

                            } catch (JSONException e) {
                                return;
                            }

                            addMessage(time, message, Messages.TYPE_MESSAGE_REMOTE);

                        }
                    }
                });
            }

        }
    };


    private void addMessage(String time, String message, int type) {
        messageList.add(new Messages.Builder(type).time(time).message(message).build());
        chatAdapterRecyclerview.notifyItemInserted(messageList.size() - 1);
//        scrollToBottom();

    }

//    private void scrollToBottom() {
////        chatrecyclerview.scrollToPosition(chatAdapterRecyclerview.getItemCount() - 1);
//    }

/*
    private Emitter.Listener onTyping = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (activity!= null)
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String typingMess = args[0].toString();
                        animFadeIn = AnimationUtils.loadAnimation(activity, R.anim.fade_in);
                        animFadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);

                        binding.txtTyping.setAnimation(animFadeIn);
                        binding.txtTyping.setVisibility(View.VISIBLE);
                        if (!mTyping)
                            mTyping = true;

                        if (mTyping) {
                            mTypingHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    binding.txtTyping.setVisibility(View.GONE);
                                    binding.txtTyping.setAnimation(animFadeOut);
                                    mTyping = false;
                                }
                            }, 1500);
                        }
                    }
                });
        }
    };
*/

//    private void getOldChats(String roomId) {
//
//        new RetrofitService(getActivity(), ServiceUrls.OLDMESSAGES + "?room_id=" + roomId + "&pageNo=" + currentPage
//                +"&pageSize=1000000",
//                1, 1, new JsonObject(), true, this).callService(false);
//    }

    @Override
    public void onStart() {
        super.onStart();
        if (!mSocket.connected()) {
            mSocket.connect();
            Log.d(TAG, "onStart: " + mSocket.connect());
        }
        mSocket.emit("set-user-data", userId);
    }



    @Override
    public void onResume() {
        super.onResume();
        mSocket.connect();
        username = CSPreferences.readString(activity, Utils.USERNAME);
        userId = CSPreferences.readString(activity, Utils.USERID);
//        toUser = "61d536de8d6ba67d290ed201";

//        toUser =  PreferenceHelper.getInstance(activity).getfriendname();
        String currentRoom = userId + "-" + toUser;
        String reverseRoom = toUser + "-" + userId;
        Log.d(TAG, "current room " + currentRoom + "reverse room " + reverseRoom);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("covsersatioFrom", userId);
            jsonObject.put("covsersatioTo", toUser);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        mSocket.emit("set-room", jsonObject);
        mSocket.emit("set-user-data", userId);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
        mSocket.off(Socket.EVENT_CONNECT, onConnect);
        mSocket.off("set-room", onGettingRoomId);
        mSocket.off("new message", onNewMessage);
        // mSocket.off("typing", onTyping);
    }

    @Override
    public void showDialog(Activity activity) {
        Utils.showDialogMethod(activity,activity.getFragmentManager());

    }

    @Override
    public void hideDialog() {
        Utils.hideDialog();

    }

    @Override
    public void showMessage(Activity activity, String msg) {
        Utils.showMessage(activity,msg);

    }
}