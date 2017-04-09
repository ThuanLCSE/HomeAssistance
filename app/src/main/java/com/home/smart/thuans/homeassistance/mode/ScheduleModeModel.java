package com.home.smart.thuans.homeassistance.mode;

import com.home.smart.thuans.homeassistance.device.SensorModel;

import java.io.Serializable;
import java.sql.Time;

/**
 * Created by Sam on 4/9/2017.
 */

public class ScheduleModeModel implements Serializable {
    private HouseModeModel houseModeModel;
    private String time;

    public HouseModeModel getHouseModeModel() {
        return houseModeModel;
    }

    public ScheduleModeModel(HouseModeModel houseModeModel, String time) {
        this.houseModeModel = houseModeModel;
        this.time = time;
    }

    public void setHouseModeModel(HouseModeModel houseModeModel) {
        this.houseModeModel = houseModeModel;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
