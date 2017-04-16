package com.home.smart.thuans.homeassistance.setup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.home.smart.thuans.homeassistance.R;

import java.util.Locale;

public class SetupFirst extends AppCompatActivity {
    private static final String TAG = "SetupFirst";
    private TextToSpeech tts;
    private String botName;
    private String[] items = new String[]{"bạn bè", "ông quản gia"}; //get data from server first

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
        ImageButton btnSpeak = (ImageButton) findViewById(R.id.btnBotNameSpeak);
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botName = txtBotName.getText().toString();
                tts.speak(botName, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        Spinner dropdown = (Spinner)findViewById(R.id.spinBotRole);

        MyCustomAdapter adapter = new MyCustomAdapter(this, android.R.layout.simple_spinner_item, items);
        dropdown.setAdapter(adapter);
        ImageButton btnDone = (ImageButton) findViewById(R.id.btnSetup1Done);

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


    public class MyCustomAdapter extends ArrayAdapter<String> {

        public MyCustomAdapter(Context context, int textViewResourceId,
                               String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.spinner_items, parent, false);
            TextView label =(TextView)row.findViewById(R.id.spinneritems);
            label.setText(items[position]);
            label.setTextColor(Color.parseColor("#FFFFFF"));

            return row;
        }
    }
}
