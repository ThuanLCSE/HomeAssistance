package com.home.smart.thuans.homeassistance.status;

/**
 * Created by Sam on 4/8/2017.
 */

public class HouseStatusModel {
    private String statusName;
    private String statusValue;

    public HouseStatusModel(String statusName, String statusValue) {
        this.statusName = statusName;
        this.statusValue = statusValue;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }
}
