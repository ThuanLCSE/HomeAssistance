package com.home.smart.thuans.homeassistance.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Thuans on 4/6/2017.
 */

public class HouseControlCenter {
    private static HouseControlCenter singletonHouse;
    private RequestQueue queue;
    private static Context context;
    public static final String CENTER_ADDRESS = "http://192.168.8.150:8080/";
    public static final String LOGIN_API = "http://192.168.8.1:8080/mobilelogin";


    private HouseControlCenter(Context context) {
        this.context = context;
        queue = getRequestQueue();
    }
    public RequestQueue getRequestQueue() {
        if (queue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            queue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return queue;
    }
    public static synchronized HouseControlCenter getInstance(Context context) {
        if (singletonHouse == null) {
            singletonHouse = new HouseControlCenter(context);
        }
        return singletonHouse;
    }
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
