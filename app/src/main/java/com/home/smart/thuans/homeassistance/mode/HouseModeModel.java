package com.home.smart.thuans.homeassistance.mode;

/**
 * Created by Thuans on 4/6/2017.
 */

public class HouseModeModel {
    private int icon;
    private String name;
    private String priority;

    public HouseModeModel(int ic, String name) {
        super();
        this.setIcon(ic);
        this.setName(name);
    }

    public int getIcon() {
        System.out.println("ahiahii");
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
