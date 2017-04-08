package com.home.smart.thuans.homeassistance.device;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.home.smart.thuans.homeassistance.R;
import com.home.smart.thuans.homeassistance.setup.SetupThird;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Thuans on 4/6/2017.
 */

public class RenameDialog extends Dialog {
    public Activity c;
    public Dialog d;
    public Button yes, no;
    public EditText dname;
    public DeviceModel deviceItem;
    public ListView lw;
    public List<DeviceModel> ItemList;
    public int ItemPos;

    private boolean flag = false;

    public RenameDialog(Activity context, DeviceModel deviceItem , List<DeviceModel> ItemList, int pos, ListView lw) {
        super(context);
        this.c = context;
        this.deviceItem = deviceItem;
        this.ItemList = ItemList;
        this.ItemPos = pos;
        this.lw = lw;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rename_device_dialog);
        dname = (EditText) findViewById(R.id.editText);
        dname.setText(deviceItem.getName());
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = true;
                Toast toast = Toast.makeText(c, "Update Successful", Toast.LENGTH_SHORT);
                toast.show();
                ItemList.get(ItemPos).setName(((EditText) findViewById(R.id.editText)).getText().toString());
                DeviceListAdapter adapter = new DeviceListAdapter(c, R.layout.device_list , ItemList);
                lw.setAdapter(adapter);
                dismiss();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
                dismiss();
            }
        });
    }
}
