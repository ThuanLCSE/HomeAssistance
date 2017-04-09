package com.home.smart.thuans.homeassistance.mode;

import java.io.Serializable;

/**
 * Created by Thuans on 4/6/2017.
 */

public class HouseModeModel implements Serializable{
    private int icon;
    private String name;

    public HouseModeModel(int ic, String name) {
        super();
        this.setIcon(ic);
        this.setName(name);
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
}
