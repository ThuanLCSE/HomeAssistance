package com.home.smart.thuans.homeassistance.device;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.home.smart.thuans.homeassistance.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thuans on 3/30/2017.
 */

public class DeviceListAdapter extends ArrayAdapter<DeviceModel>{
    private static final String TAG = "DeviceListAdapter";
    private TextView deviceName;
    private ImageView deviceIcon;
    private List<DeviceModel> deviceItemList = new ArrayList<DeviceModel>();
    private Context context;


    public DeviceListAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        this.context = context;
    }
    public DeviceListAdapter(@NonNull Context context, @LayoutRes int resource, List<DeviceModel> listDevice) {
        super(context, resource);
        this.context = context;
        this.deviceItemList = listDevice;
    }


    public int getCount() {
        return this.deviceItemList.size();
    }

    public DeviceModel getItem(int index) {
        return this.deviceItemList.get(index);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("Device Item","Get view---------------");
        DeviceModel device= getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        row = inflater.inflate(R.layout.device_list, parent, false);
        deviceIcon =  (ImageView) row.findViewById(R.id.deviceIcon);
        deviceIcon.setImageResource(device.getIcon());
        deviceName = (TextView) row.findViewById(R.id.deviceName);
        deviceName.setText(device.getName());
        return row;
    }

}
