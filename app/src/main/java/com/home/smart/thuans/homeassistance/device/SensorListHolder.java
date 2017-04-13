package com.home.smart.thuans.homeassistance.device;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.home.smart.thuans.homeassistance.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 4/13/2017.
 */

public class SensorListHolder extends RecyclerView.ViewHolder{
    private static final String TAG = "SensorListHolder";
    public TextView sensorName;
    public ImageView sensorIcon;
    public TextView sensorValue;
    public List<SensorModel> sensorModelList = new ArrayList<SensorModel>();
    public Context context;

    public SensorListHolder(View rowView) {
        super(rowView);

        sensorName = (TextView) rowView.findViewById(R.id.modeName);
        sensorIcon= (ImageView) rowView.findViewById(R.id.sensorIcon);
        sensorValue= (TextView) rowView.findViewById(R.id.sensorValue);



    }


}
