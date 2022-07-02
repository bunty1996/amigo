package com.app.humanresource.Utils.ChatApplication

import android.app.Application
import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import java.net.Socket
import android.net.ConnectivityManager

import android.content.IntentFilter

import android.os.Build

import android.os.Bundle
import com.app.amigo.Utils.MyNetworkReceiver
import com.github.nkzawa.socketio.client.IO
import java.net.URISyntaxException


class ChatApplication : Application() {
    private val TAG = "ChatApplication"
    private var mNetworkReceiver: MyNetworkReceiver? = null
    var mSocket: com.github.nkzawa.socketio.client.Socket? = null


    companion object {
        var mActivity: Activity? = null
        const val URL_CHAT_SERVER = "http://34.231.88.85:8001"


    }


    fun ss() {
        try {
            mSocket = IO.socket(URL_CHAT_SERVER)
        } catch (e: URISyntaxException) {

        }

    }


//    {
//        mSocket = try {
//            IO.socket(ServiceUrls.URL_CHAT_SERVER)
//        } catch (URISyntaxException e) {
//            throw new RuntimeException e
//        }
//
//    }

    fun getmSocket(): com.github.nkzawa.socketio.client.Socket? {
        return mSocket
    }


    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                mNetworkReceiver = MyNetworkReceiver()
            }

            override fun onActivityStarted(activity: Activity) {
                mActivity = activity
            }

            override fun onActivityResumed(activity: Activity) {
//                Log.d(TAG,mActivity.getLocalClassName());
//                mActivity=activity;
//                registerBroadcastForNaugot();
            }

            override fun onActivityPaused(activity: Activity) {
                mActivity = null
                //     unregisterReceiver(mNetworkReceiver);
            }

            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        })
    }

    private fun registerBroadcastForNaugot() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(
                mNetworkReceiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
        }
    }


}