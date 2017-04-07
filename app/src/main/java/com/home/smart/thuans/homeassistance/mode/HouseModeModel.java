package com.home.smart.thuans.homeassistance.mode;

/**
 * Created by Thuans on 4/6/2017.
 */

public class HouseModeModel {
    private int iconID;
    private String name;

    public HouseModeModel(int ic, String name) {
        super();
        this.setIcon(ic);
        this.setName(name);
    }

    public int getIcon() {
        return iconID;
    }

    public void setIcon(int icon) {
        this.iconID = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
