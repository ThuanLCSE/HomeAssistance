package com.home.smart.thuans.homeassistance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.home.smart.thuans.homeassistance.setup.SetupFirst;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Main","Starting app");
        startService(new Intent(MainActivity.this, SetupFirst.class));
        Button btnEnterSetup = (Button) findViewById(R.id.btnEnterSetup);

        btnEnterSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SetupFirst.class));
            }
        });



    }
}
