package com.home.smart.thuans.homeassistance.device;

import java.util.Date;

/**
 * Created by Thuans on 4/7/2017.
 */

public class DeviceModel {
    private int id;
    private int icon;
    private String name;
    private int port;
    private String state;
    private String type;
    private String value;
    private String feature;
    public DeviceModel(int ic, String name) {
        super();
        this.icon = ic;
        this.name= name;
    }

    public DeviceModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public DeviceModel(int id, String name, int port, String state, String type, String value,  String feature) {
        this.id = id;
        this.name = name;
        this.port = port;
        this.state = state;
        this.type = type;
        this.value = value;
        this.feature = feature;
    }
}
