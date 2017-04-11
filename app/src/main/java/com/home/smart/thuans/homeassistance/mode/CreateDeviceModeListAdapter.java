package com.home.smart.thuans.homeassistance.mode;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.home.smart.thuans.homeassistance.R;
import com.home.smart.thuans.homeassistance.device.DeviceControlModel;
import com.home.smart.thuans.homeassistance.device.DeviceModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 4/11/2017.
 */

public class CreateDeviceModeListAdapter extends ArrayAdapter<DeviceModel> {
    private static final String TAG = "CreateDeviceModeListAdapter";
    private List<DeviceModel> listDevice;
    private Context context;
    private TextView deviceName;

    public CreateDeviceModeListAdapter(@NonNull Context context, @LayoutRes int resource, List<DeviceModel> listDevice) {
        super(context, resource);
        this.listDevice = listDevice;
        this.context = context;
    }

    public int getCount() {
        return listDevice.size();
    }

    public long getItemId(int position) {
        return position;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater li = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.device_create_mode_list, null);
        }

        deviceName = (TextView) convertView.findViewById(R.id.deviceName);
        deviceName.setText(listDevice.get(position).getName());

        return convertView;
    }
}
