package com.home.smart.thuans.homeassistance.mode;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.home.smart.thuans.homeassistance.R;

import java.util.List;

/**
 * Created by Sam on 4/9/2017.
 */

public class ScheduleModeListAdapter extends ArrayAdapter<ScheduleModeModel> {
    private View view;
    private TextView modeTime;
    private TextView  modeName;
    private List<ScheduleModeModel> listSchuduleMode;
    private String TAG = "ScheduleModeListAdapter";

    public ScheduleModeListAdapter(@NonNull Activity context, @LayoutRes int resource, List<ScheduleModeModel> listSchuduleMode ) {
        super(context, resource);
        this.listSchuduleMode = listSchuduleMode;
    }

    //
    public int getCount() {
        return listSchuduleMode.size();
    }

    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        view = convertView;
        if (view == null){
            LayoutInflater li = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.mode_schedule_list, null);
        }
        modeTime = (TextView) view.findViewById(R.id.modeTime);
        modeName = (TextView) view.findViewById(R.id.scheduleName);

        modeTime.setText(listSchuduleMode.get(position).getTime());
        modeName.setText(listSchuduleMode.get(position).getHouseModeModel().getName());

        return view ;
    }
}
