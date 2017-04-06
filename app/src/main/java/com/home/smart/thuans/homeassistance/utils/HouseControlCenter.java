package com.home.smart.thuans.homeassistance.utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Thuans on 4/6/2017.
 */

public class HouseControlCenter {
    private RequestQueue queue;

    public HouseControlCenter(Context context) {
        if (this.queue == null){
            this.queue = Volley.newRequestQueue(context);
        }
    }

    public RequestQueue getQueue() {
        return queue;
    }
}
