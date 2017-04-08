package com.home.smart.thuans.homeassistance.status;

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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 4/8/2017.
 */

public class HouseStatusListAdapter extends ArrayAdapter<HouseStatusModel>{

    private TextView statusName;
    private TextView statusValue;
    private List<HouseStatusModel> statuslist = new ArrayList<HouseStatusModel>();
    private Context context;

    public HouseStatusListAdapter(@NonNull Context context, @LayoutRes int resource, List<HouseStatusModel> statuslist) {
        super(context, resource);
        this.statuslist = statuslist;
        this.context = context;
    }

    public int getCount() {
        return statuslist.size();
    }

    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View view = convertView;
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R.layout.house_status_list, null);
        }

        statusName = (TextView) view.findViewById(R.id.statusName);
        statusValue = (TextView) view.findViewById(R.id.statusValue);

        statusName.setText(statuslist.get(position).getStatusName());
        statusValue.setText(statuslist.get(position).getStatusValue());


        return view;
    }
}
