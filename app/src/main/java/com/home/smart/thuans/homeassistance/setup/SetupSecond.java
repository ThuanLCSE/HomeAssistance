package com.home.smart.thuans.homeassistance.setup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.home.smart.thuans.homeassistance.R;

public class SetupSecond extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_second);
        Button btnDone = (Button) findViewById(R.id.btnSetup2Done);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetupSecond.this, SetupThird.class));
            }
        });
    }
}