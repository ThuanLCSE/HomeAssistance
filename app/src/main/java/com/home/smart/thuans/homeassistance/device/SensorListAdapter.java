package com.home.smart.thuans.homeassistance.device;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class SensorListAdapter  extends ArrayAdapter<SensorModel> {
    private static final String TAG = "SensorListAdapter";
    private TextView sensorName;
    private ImageView sensorIcon;
    private TextView sensorValue;
    private List<SensorModel> sensorModelList = new ArrayList<SensorModel>();
    private Context context;

    public SensorListAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }
    public  SensorListAdapter(@NonNull Context context, @LayoutRes int resource, List<SensorModel> sensorList) {
        super(context, resource);
        this.context = context;
        this.sensorModelList= sensorList;
    }
    public int getCount() {
        return this.sensorModelList.size();
    }

    public SensorModel getItem(int index) {
        return this.sensorModelList.get(index);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater li = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = li.inflate(R.layout.sensor_list, null);
        }
        sensorName = (TextView) rowView.findViewById(R.id.sensorName);
        sensorIcon= (ImageView) rowView.findViewById(R.id.sensorIcon);
        sensorValue= (TextView) rowView.findViewById(R.id.sensorValue);

        sensorName.setText(sensorModelList.get(position).getName());
        sensorIcon.setImageResource(sensorModelList.get(position).getIcon());
        sensorValue.setText(sensorModelList.get(position).getValue());

        return rowView;
    }
}
