package com.home.smart.thuans.homeassistance.mode;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.home.smart.thuans.homeassistance.R;

import java.util.List;



/**
 * Created by Sam on 4/8/2017.
 */

public class EditModeDialog extends Dialog {
    private static final String TAG = "EditModeDialog";
    public Button save;
    public Button del;
    public View view;
    public List<HouseModeModel> itemList;
    public int itemPos;
    public TextView modeName;
    public ImageView modeIcon;
    public Activity context;
    public ListView lw;


    public EditModeDialog(Activity context, List<HouseModeModel> itemList, int position,
                          View view, ListView lw) {
        super(context);
        this.context = context;
        this.itemList = itemList;
        this.itemPos = position;
        this.view = view;
        this.lw = lw;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.editmode_dialog);
        save = (Button) findViewById(R.id.btnSaveMode);
        del = (Button) findViewById(R.id.btnDelMode);
        modeName = (TextView) findViewById(R.id.modeName);
        modeName.setText(itemList.get(itemPos).getName());
        modeIcon = (ImageView) findViewById(R.id.modeImage);
        modeIcon.setImageResource(itemList.get(itemPos).getIcon());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Save onClick: ");
                // update itemList -> get adapter -> set listview again -> update listview in fragement
                itemList.get(itemPos).setName(((EditText) findViewById(R.id.modeName)).getText().toString());
                HouseModeListAdapter adapter = new HouseModeListAdapter(context, R.layout.house_mode_list, itemList);
                adapter.setListView(lw);
                lw.setAdapter(adapter);


                Toast toast = Toast.makeText(getContext(), "Chế độ được cập nhật", Toast.LENGTH_SHORT);
                toast.show();
                dismiss();

            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Del onClick: ");
                // remove item in List -> get adapter -> set listview again -> update listview in fragement

                itemList.remove(itemPos);

                HouseModeListAdapter adapter = new HouseModeListAdapter(context, R.layout.house_mode_list, itemList);
                adapter.setListView(lw);
                lw.setAdapter(adapter);

                Toast toast = Toast.makeText(getContext(), "Chế độ được cập nhật", Toast.LENGTH_SHORT);
                toast.show();
                dismiss();
            }
        });

    }
}
