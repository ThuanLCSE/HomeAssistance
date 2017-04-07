package com.home.smart.thuans.homeassistance.device;

import com.home.smart.thuans.homeassistance.R;
import com.home.smart.thuans.homeassistance.utils.DeviceConstant;

/**
 * Created by Thuans on 4/7/2017.
 */

public class SensorModel {

    private int icon;
    private String name;
    private String value;
    private String feature;

    public SensorModel(int icon, String name, String value, String feature) {
        this.icon = icon;
        this.name = name;
        this.value = value;
        this.feature = feature;
        if (this.feature.equals(DeviceConstant.SENSOR_LIGHT)){
            this.icon = R.drawable.light;
        } else  if (this.feature.equals(DeviceConstant.SENSOR_DOOR)){
            this.icon = R.drawable.door;
        } else  if (this.feature.equals(DeviceConstant.SENSOR_TEMP)){
            this.icon = R.drawable.temp;
        }
    }
    public int getIcon() {
        return icon;
    }

    public String getFeature() {
        return feature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
