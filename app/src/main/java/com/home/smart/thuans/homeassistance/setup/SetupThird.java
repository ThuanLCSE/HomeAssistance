package com.home.smart.thuans.homeassistance.setup;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
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

import layout.ChatFragment;

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

        ImageButton btnDone = (ImageButton) findViewById(R.id.btnSetup3Done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Done onClick: ");
                startActivity(new Intent(SetupThird.this, HomeActivity.class));

                NotificationCompat.Builder notBuilder = new NotificationCompat.Builder(SetupThird.this);

                notBuilder.setSmallIcon(R.mipmap.ic_launcher);
                notBuilder.setTicker("This is a ticker");


                notBuilder.setWhen(System.currentTimeMillis()+ 10* 1000);
                notBuilder.setContentTitle("This is title");
                notBuilder.setContentText("This is content text ....");


                Intent intent = new Intent(SetupThird.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("noti", 111111);
                Log.d(TAG, "onClick: " + intent.getExtras());



                PendingIntent pendingIntent = PendingIntent.getActivity(SetupThird.this, 0,
                        intent, 0);

                notBuilder.setContentIntent(pendingIntent);

                NotificationManager notificationService  =
                        (NotificationManager) SetupThird.this.getSystemService(Context.NOTIFICATION_SERVICE);

                Notification notification =  notBuilder.build();
                notificationService.notify(1, notification);

            }
        });

    }

}
