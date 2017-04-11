package layout;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.home.smart.thuans.homeassistance.R;
import com.home.smart.thuans.homeassistance.mode.CreateModeDialog;
import com.home.smart.thuans.homeassistance.mode.HouseModeListAdapter;
import com.home.smart.thuans.homeassistance.mode.HouseModeModel;
import com.home.smart.thuans.homeassistance.mode.ScheduleModeListAdapter;
import com.home.smart.thuans.homeassistance.mode.ScheduleModeModel;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class HouseModeFragment extends Fragment {
    private static final String TAG = "HouseModeFragment";
    private ListView lw;
    private List<HouseModeModel> housemodeList = new ArrayList<>();;
    private List<ScheduleModeModel> scheduleModeList = new ArrayList<>();;
    private Button btnCreate;


    private String[] modeName ={
            "Đón khách",
            "Đi ngủ",
            "Sáng sớm",
            "Ăn tối",
            "Xem phim"
    };

    private String[] modeTime ={
           "6:45",
            "12:00",
            "15:15",
            "17:35",
            "21:00"
    };



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_house_mode,container,false);


        if (housemodeList.isEmpty()) {
            for (int i = 0 ; i< modeName.length ; i ++) {
                HouseModeModel HMmodel = new HouseModeModel(R.drawable.light, modeName[i]);
                ScheduleModeModel SMmodel = new ScheduleModeModel(HMmodel, modeTime[i]);
                this.housemodeList.add(HMmodel);
                this.scheduleModeList.add(SMmodel);
            }
        }



        ListView listMode= (ListView) rootView.findViewById(R.id.lstHouseMode);
        HouseModeListAdapter adapter = new HouseModeListAdapter(this.getActivity(), R.layout.house_mode_list, housemodeList);
        adapter.setListView(listMode);
        listMode.setAdapter(adapter);

        ListView listScheduleMode = (ListView) rootView.findViewById(R.id.lstScheduleMode);
        ScheduleModeListAdapter scheduleAdapter = new ScheduleModeListAdapter(this.getActivity(), R.layout.mode_schedule_list, scheduleModeList);
        listScheduleMode.setAdapter(scheduleAdapter);
        Log.e(TAG, "onCreateView: Size " + scheduleModeList.size());

        btnCreate = (Button) rootView.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateModeDialog cmd = new CreateModeDialog(HouseModeFragment.this.getContext());
                cmd.show();
            }
        });

        return rootView;
    }


}