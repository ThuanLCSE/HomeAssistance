package com.home.smart.thuans.homeassistance.device;

import com.home.smart.thuans.homeassistance.R;
import com.home.smart.thuans.homeassistance.utils.DeviceConstant;

/**
 * Created by Thuans on 4/7/2017.
 */

public class SensorModel {

    private int icon;
    private String name;
    private int port;
    private String value;
    private String feature;

    public SensorModel(String name, String value, String feature, int port) {

        this.name = name;
        this.value = value;
        this.setPort(port);
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

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
