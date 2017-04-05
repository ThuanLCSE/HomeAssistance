package com.home.smart.thuans.homeassistance.device;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.home.smart.thuans.homeassistance.R;

/**
 * Created by Thuans on 3/31/2017.
 */

public class RenameFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rename_fragment, container, false);
    }

}
