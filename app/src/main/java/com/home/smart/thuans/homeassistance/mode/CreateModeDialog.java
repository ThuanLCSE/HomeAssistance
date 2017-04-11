package com.home.smart.thuans.homeassistance.mode;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;

import com.home.smart.thuans.homeassistance.R;
import com.home.smart.thuans.homeassistance.device.DeviceModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sam on 4/11/2017.
 */

public class CreateModeDialog extends Dialog {
    private static final String TAG = "CreateModeDialog";

    private List<DeviceModel> deviceItemList = new ArrayList<DeviceModel>();
    private ListView lw;
    String[] itemname ={
            "Đèn chùm phòng ngủ",
            "Đèn phòng khách",
            "Quạt phòng ngủ",
            "Máy lạnh phòng khách",
            "Đo nhiệt độ phòng khách",
            "Đo ánh sáng phòng ngủ"
    };
    int[] itemIcon ={
            R.drawable.bulb,
            R.drawable.bulb,
            R.drawable.fan,
            R.drawable.air,
            R.drawable.temp,
            R.drawable.light
    };



    public CreateModeDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.create_mode_dialog);

        for (int i = 0 ; i< itemname.length ; i ++){
            this.deviceItemList.add(new DeviceModel(itemIcon[i],itemname[i]));
        }

        lw = (ListView) findViewById(R.id.lstDevice);
        CreateDeviceModeListAdapter adapter = new CreateDeviceModeListAdapter(this.getContext(), R.layout.create_mode_dialog, deviceItemList);
        Log.e(TAG, "onCreate: Size" + deviceItemList.size());
        lw.setAdapter(adapter);


    }
}
