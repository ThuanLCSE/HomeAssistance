package com.home.smart.thuans.homeassistance.mode;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.home.smart.thuans.homeassistance.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thuans on 4/7/2017.
 */

public class HouseModeListAdapter extends ArrayAdapter<HouseModeModel> {

    private TextView modeName;
    private ImageView modeIcon;
    private List<HouseModeModel> modeList = new ArrayList<HouseModeModel>();
    private Context context;
    private View rowView;

    public HouseModeListAdapter(Context context) {
        super(context, R.layout.house_mode_list);
        this.context = context;
    }
    public  HouseModeListAdapter(@NonNull Context context, @LayoutRes int resource, List<HouseModeModel> modeList) {
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
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
          rowView = convertView;

        if (rowView == null) {
            LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = vi.inflate(R.layout.house_mode_list, null);
        }
        modeName = (TextView) rowView.findViewById(R.id.modeName);
        modeIcon = (ImageView) rowView.findViewById(R.id.modeIcon);
        Button btnEdit = (Button)rowView.findViewById(R.id.btnEditMode);


        modeName.setText(modeList.get(position).getName());
        modeIcon.setImageResource(modeList.get(position).getIcon());

        btnEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                EditModeDialog emDialog = new EditModeDialog(getContext(), modeList, position, rowView);
                emDialog.show();
            }
        });
        return rowView;
    }
}