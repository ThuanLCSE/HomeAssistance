package com.home.smart.thuans.homeassistance.setup;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.home.smart.thuans.homeassistance.HomeActivity;
import com.home.smart.thuans.homeassistance.MainActivity;
import com.home.smart.thuans.homeassistance.R;
import com.home.smart.thuans.homeassistance.device.DeviceListAdapter;
import com.home.smart.thuans.homeassistance.device.DeviceModel;
import com.home.smart.thuans.homeassistance.device.RenameDialog;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SetupThird extends AppCompatActivity {
    private static final String TAG = "SetupThird";
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
    List<DeviceModel> deviceItemList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_third);

        for (int i = 0 ; i< itemname.length ; i ++){
            this.deviceItemList.add(new DeviceModel(itemIcon[i],itemname[i]));
        }
        DeviceListAdapter adapter = new DeviceListAdapter(this, R.layout.device_list , deviceItemList);

        final ListView listDevice = (ListView)findViewById(R.id.lstDevice);
        listDevice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DeviceModel item = (DeviceModel) parent.getItemAtPosition(position);
                RenameDialog adb = new RenameDialog(
                        SetupThird.this, item, deviceItemList, position, listDevice);
                adb.setTitle("List");
                adb.show();
            }
        });
        listDevice.setAdapter(adapter);

//        FragmentManager fragmentManager = getFragmentManager();
//        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        RenameFragment renameFragment = new RenameFragment();
//        fragmentTransaction.replace(android.R.id.content, renameFragment);

        Button btnDone = (Button) findViewById(R.id.btnSetup3Done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Done onClick: ");
                startActivity(new Intent(SetupThird.this, HomeActivity.class));
            }
        });

    }

}
