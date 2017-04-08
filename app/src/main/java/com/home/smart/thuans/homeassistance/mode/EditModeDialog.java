package com.home.smart.thuans.homeassistance.mode;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.home.smart.thuans.homeassistance.R;

import java.util.List;

import layout.HouseModeFragment;


/**
 * Created by Sam on 4/8/2017.
 */

public class EditModeDialog extends Dialog {
    public Button save;
    public Button del;
    public View view;
    public List<HouseModeModel> itemList;
    public int itemPos;
    public TextView modeName;
    public ImageView modeIcon;


    public EditModeDialog(Context context, List<HouseModeModel> itemList, int position, View view) {
        super(context);
        this.itemList = itemList;
        this.itemPos = position;
        this.view = view;
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
                HouseModeFragment hmf = new HouseModeFragment();
                String[] modeList = hmf.getmodeNameList();
                for (int i = 0; i < modeList.length; i++ ) {
                    if (modeList[i].equals(itemList.get(itemPos).getName())){
                        modeList[i] = modeName.getText().toString();
                        hmf.setmodeNameList(modeList);
                        break;
                    };
                }
//                HouseModeListAdapter adapter = new HouseModeListAdapter(hmf.getContext(), R.layout.house_mode_list, hmf.gethousemodeList());
//                hmf.lw.setAdapter(adapter);
                Toast toast = Toast.makeText(getContext(), "Chế độ được cập nhật", Toast.LENGTH_SHORT);
                toast.show();
                dismiss();

            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
}
