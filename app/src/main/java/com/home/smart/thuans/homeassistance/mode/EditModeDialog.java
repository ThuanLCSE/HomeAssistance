package com.home.smart.thuans.homeassistance.mode;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.home.smart.thuans.homeassistance.R;
import com.home.smart.thuans.homeassistance.device.DeviceListAdapter;


/**
 * Created by Sam on 4/8/2017.
 */

public class EditModeDialog extends Dialog {


    public EditModeDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.editmode_dialog);
    }
}
