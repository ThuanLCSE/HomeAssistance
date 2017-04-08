package layout;


import android.os.Bundle;
import android.support.annotation.Nullable;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.home.smart.thuans.homeassistance.R;
import com.home.smart.thuans.homeassistance.device.DeviceModel;
import com.home.smart.thuans.homeassistance.mode.EditModeDialog;
import com.home.smart.thuans.homeassistance.mode.HouseModeListAdapter;
import com.home.smart.thuans.homeassistance.mode.HouseModeModel;

import java.util.ArrayList;
import java.util.List;

public class HouseModeFragment extends Fragment {
    private List<HouseModeModel> housemodeList = new ArrayList<HouseModeModel>();
    private String[] modeName ={
            "Đón khách",
            "Đi ngủ",
            "Sáng sớm",
            "Ăn tối",
            "Xem phim"
    };

     FragmentManager fm = getFragmentManager();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_house_mode,container,false);


        for (int i = 0 ; i< modeName.length ; i ++){
            this.housemodeList.add(new HouseModeModel(R.drawable.light,modeName[i]));
        }
        HouseModeListAdapter adapter = new HouseModeListAdapter(this.getContext(), R.layout.house_mode_list, housemodeList);

        ListView listMode= (ListView) rootView.findViewById(R.id.lstHouseMode);
        listMode.setAdapter(adapter);


        return rootView;
    }


}