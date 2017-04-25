package com.example.user.fazooz;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class MyGlobals{
    Context mContext;

    // constructor
    public MyGlobals(Context context){
        this.mContext = context;
    }

    public boolean isNetworkConnected() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;
        return  connected;
    }
}