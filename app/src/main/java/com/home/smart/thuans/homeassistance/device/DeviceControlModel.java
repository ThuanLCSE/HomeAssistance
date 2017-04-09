package com.home.smart.thuans.homeassistance.device;

/**
 * Created by Thuans on 4/7/2017.
 */

public class DeviceControlModel {
    private int id;
    private int icon;
    private String name;
    private int port;
    private String state;
    private String feature;

    public int getIcon() {
        return icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}
