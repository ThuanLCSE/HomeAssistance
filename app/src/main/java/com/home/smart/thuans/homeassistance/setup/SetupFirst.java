package com.home.smart.thuans.homeassistance.setup;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.home.smart.thuans.homeassistance.R;

import java.util.Locale;

public class SetupFirst extends AppCompatActivity {
    private TextToSpeech tts;
    private String botName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_first);
        Log.d("Set up 1: ","First -----------------");
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                Log.d("Set up 1: ",Locale.getDefault().getDisplayLanguage());

                if(status != TextToSpeech.ERROR) {
                    tts.setLanguage(new Locale("vi","VN"));
                }
            }
        });
        final EditText txtBotName =(EditText)findViewById(R.id.txtBotName);
        Button btnSpeak = (Button) findViewById(R.id.btnBotNameSpeak);
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botName = txtBotName.getText().toString();
                tts.speak(botName, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        Spinner dropdown = (Spinner)findViewById(R.id.spinBotRole);
        String[] items = new String[]{"bạn bè", "ông quản gia"}; //get data from server first
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        Button btnDone = (Button) findViewById(R.id.btnSetup1Done);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetupFirst.this, SetupSecond.class));
            }
        });
    }
    public void onPause(){
        if(tts !=null){
            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }
}
