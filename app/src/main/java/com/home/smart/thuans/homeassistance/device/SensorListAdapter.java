package com.home.smart.thuans.homeassistance.device;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.home.smart.thuans.homeassistance.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thuans on 4/7/2017.
 */

public class SensorListAdapter  extends RecyclerView.Adapter<SensorListHolder> {
    private static final String TAG = "SensorListAdapter";
    private List<SensorModel> sensorModelList = new ArrayList<SensorModel>();

    public SensorListAdapter(List<SensorModel> sensorModelList) {
        this.sensorModelList = sensorModelList;
    }

    @Override
    public SensorListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sensor_list, parent, false);
        SensorListHolder holder = new SensorListHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return sensorModelList.size();
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(SensorListHolder holder, int position) {
        holder.sensorName.setText(sensorModelList.get(position).getName());
        holder.sensorIcon.setImageResource(sensorModelList.get(position).getIcon());
        holder.sensorValue.setText(sensorModelList.get(position).getValue());
    }


}
