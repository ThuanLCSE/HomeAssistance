package com.home.smart.thuans.homeassistance.mode;

import android.app.Activity;

import android.content.Context;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.home.smart.thuans.homeassistance.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Thuans on 4/7/2017.
 */

public class HouseModeListAdapter extends ArrayAdapter<HouseModeModel> {
    private static final String TAG = "HouseModeListAdapter";
    private TextView modeName;
    private List<HouseModeModel> modeList = new ArrayList<HouseModeModel>();
    private Activity context;
    private View rowView;
    private ListView lw;
//    private Switch sw;

    private SlideDateTimeListener listener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date)
        {
            // Do something with the date. This Date object contains
            // the date and time that the user has selected.
            Log.e(TAG, "onDateTimeSet: date" + date);
        }

        @Override
        public void onDateTimeCancel()
        {
            // Overriding onDateTimeCancel() is optional.
        }
    };

    public void setListView(ListView lw) {
        this.lw = lw;
    }


    public  HouseModeListAdapter(@NonNull Activity context, @LayoutRes int resource, List<HouseModeModel> modeList) {
        super(context, resource);
        this.context = context;
        this.modeList = modeList;
    }


    public int getCount() {
        return modeList.size();
    }

    public long getItemId(int position) {
        return position;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
          rowView = convertView;

        if (rowView == null) {
            LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = vi.inflate(R.layout.house_mode_list, null);
        }
        modeName = (TextView) rowView.findViewById(R.id.modeName);
//        Button btnEdit = (Button)rowView.findViewById(R.id.btnEditMode);
//        sw = (Switch) rowView.findViewById(R.id.swtModeOn);

        modeName.setText(modeList.get(position).getName());

//        btnEdit.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                EditModeDialog emDialog = new EditModeDialog(context, modeList, position, rowView, lw);
//                emDialog.show();
//            }
//        });

//        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                FragmentActivity activity = (FragmentActivity) HouseModeListAdapter.this.getContext();
//                if (isChecked) {
//                    new SlideDateTimePicker.Builder(activity.getSupportFragmentManager())
//                            .setListener(listener)
//                            .setInitialDate(new Date())
//                            .build()
//                            .show();
//                }
//
//            }
//        });


        return rowView;
    }
}