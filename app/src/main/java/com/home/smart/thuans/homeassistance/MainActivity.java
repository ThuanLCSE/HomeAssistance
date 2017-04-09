package com.home.smart.thuans.homeassistance;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.home.smart.thuans.homeassistance.device.DeviceModel;
import com.home.smart.thuans.homeassistance.setup.SetupFirst;
import com.home.smart.thuans.homeassistance.setup.SetupThird;
import com.home.smart.thuans.homeassistance.sqlite.DeviceSqlHelper;
import com.home.smart.thuans.homeassistance.utils.HouseControlCenter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProgressDialog mDownloadDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Main","Starting app");
        startService(new Intent(MainActivity.this, SetupFirst.class));
        Button btnEnterSetup = (Button) findViewById(R.id.btnEnterSetup);
        DeviceSqlHelper dvSql = new DeviceSqlHelper(MainActivity.this);


        btnEnterSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final DownloadTask downloadTask = new DownloadTask(MainActivity.this,"/download_file/test.txt");
//                downloadTask.execute("http://192.168.8.1:8080/api/entity/getFile");
                StringRequest loginReq = new StringRequest(Request.Method.POST,
                        HouseControlCenter.LOGIN_API, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //xu ly db
                        List<DeviceModel> houseDevice = HouseControlCenter.getInstance(MainActivity.this).
                                getListDevice();

                        mDownloadDialog.dismiss();
                        startActivity(new Intent(MainActivity.this, SetupFirst.class));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("volley Error","Error: "+error.getLocalizedMessage());
                        mDownloadDialog.dismiss();
                        startActivity(new Intent(MainActivity.this, SetupFirst.class));
                    }
                });
                RequestQueue queue = HouseControlCenter.getInstance(getApplicationContext()).
                        getRequestQueue();
                queue.add(loginReq);

                mDownloadDialog = new ProgressDialog(MainActivity.this);
                mDownloadDialog.setMessage("Tải dữ liệu....");
                mDownloadDialog.show();
            }
        });



    }

}
