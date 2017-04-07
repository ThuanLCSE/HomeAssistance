package com.home.smart.thuans.homeassistance.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Thuans on 4/6/2017.
 */

public class SQLiteConstant{
    public static final String TAG = "SQLiteHouseTag";

    //Version DB
    public static final int DB_Version = 1;
    //
    public static final String DB_Name = "house_assistance";
    public static final String DB_DEVICE_TABLE = "device";
    public static final String DB_DEVICE_SECONDARY_NAME_TABLE = "device_secondary_name";

    public static final String DB_TERM_TABLE = "term";
    public static final String DB_TERMDEVICE_TABLE = "term_device";
    public static final String DB_SENTENCE_TABLE = "Sentence";
    public static final String DB_DATE_FORMAT = "HH:mm yyyy-MM-dd";
    public static String getDateTimeSQL(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                DB_DATE_FORMAT , Locale.getDefault());
        return dateFormat.format(date);
    }
    public static Date convertToDate(String dateString){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DB_DATE_FORMAT);
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertedDate;
    }
}
