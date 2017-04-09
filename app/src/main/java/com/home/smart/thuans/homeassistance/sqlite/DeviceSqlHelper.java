package com.home.smart.thuans.homeassistance.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.home.smart.thuans.homeassistance.device.DeviceModel;
import com.home.smart.thuans.homeassistance.utils.SQLiteConstant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Thuans on 4/7/2017.
 */

public class DeviceSqlHelper extends SQLiteOpenHelper{
    private static final String TAG = "DeviceSqlHelper";
    private final String idCol = "id";
    private final String portCol = "port";
    private final String nameCol = "name";
    private final String stateCol = "state";
    private final String typeCol = "type";
    private final String valueCol = "value";
    private final String featureCol = "feature";
    private final String deviceIdCol = "deviceId";

    public DeviceSqlHelper(Context context) {
        super(context, SQLiteConstant.DB_Name, null, SQLiteConstant.DB_Version);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DEVICE_TABLE = "CREATE TABLE " + SQLiteConstant.DB_DEVICE_TABLE + "("
                + idCol + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + portCol + " INTEGER,"
                + nameCol + " TEXT,"
                + stateCol + " TEXT,"
                + typeCol + " TEXT,"
                + valueCol + " TEXT,"
                + featureCol + " TEXT" + ")";
        db.execSQL(CREATE_DEVICE_TABLE);
        String CREATE_DEVICE_SECONDARY_NAME_TABLE = "CREATE TABLE " + SQLiteConstant.DB_DEVICE_SECONDARY_NAME_TABLE + "("
                + nameCol + " TEXT,"
                + deviceIdCol + " INTEGER," +
                "PRIMARY KEY ("+nameCol+","+ deviceIdCol+"))";
        db.execSQL(CREATE_DEVICE_SECONDARY_NAME_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + SQLiteConstant.DB_DEVICE_TABLE );
        db.execSQL("DROP TABLE IF EXISTS " + SQLiteConstant.DB_DEVICE_SECONDARY_NAME_TABLE );

        onCreate(db);
    }

    public void addNewDevice(DeviceModel model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(nameCol, model.getName());
        values.put(portCol, model.getPort());
        values.put(stateCol, model.getState());
        values.put(typeCol, model.getType());
        values.put(valueCol, model.getValue());
        values.put(featureCol, model.getFeature());

        // Inserting Row
        long newId = db.insert(SQLiteConstant.DB_DEVICE_TABLE, null, values);
        if (newId != -1){
            values = new ContentValues();
            values.put(nameCol, model.getName());
            values.put(deviceIdCol, newId);
            db.insert(SQLiteConstant.DB_DEVICE_SECONDARY_NAME_TABLE, null, values);
        }
        db.close(); // Closing database connection
    }

    // Getting single contact
    public DeviceModel getDeviceByPort(int port) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + SQLiteConstant.DB_DEVICE_TABLE + " WHERE "+portCol+" = " + port;
        Cursor cursor = db.rawQuery(query, null);

        DeviceModel result = null;
        if (cursor.moveToFirst()) {
            result.setId(Integer.parseInt(cursor.getString(0)));
            result.setPort(Integer.parseInt(cursor.getString(1)));
            result.setName(cursor.getString(2));
            result.setState(cursor.getString(3));
            result.setType(cursor.getString(4));
            result.setValue(cursor.getString(5));
            result.setFeature(cursor.getString(6));
            Log.i("Device sql", "get port" + result.getName());
        }
        Log.i("Device sql", "get port not found : " + port);
        db.close();
        return result;
    }
    public List<DeviceModel> getAllDevice() {
        List<DeviceModel> deviceList = new ArrayList<DeviceModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + SQLiteConstant.DB_DEVICE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                DeviceModel model = new DeviceModel();
                model.setId(Integer.parseInt(cursor.getString(0)));
                model.setPort(Integer.parseInt(cursor.getString(1)));
                model.setName(cursor.getString(2));
                model.setState(cursor.getString(3));
                model.setType(cursor.getString(4));
                model.setValue(cursor.getString(5));
                model.setFeature(cursor.getString(6));

                deviceList.add(model);
            } while (cursor.moveToNext());
        }
        Log.d("DB-----","list size: "+deviceList.size());
        // return contact list
        return deviceList;
    }

    // Updating single contact
    public int updateDeviceById(DeviceModel model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(nameCol, model.getName());
        values.put(portCol, model.getPort());
        values.put(stateCol, model.getState());
        values.put(typeCol, model.getType());
        values.put(valueCol, model.getValue());
        values.put(featureCol, model.getFeature());

        // updating row
        return db.update(SQLiteConstant.DB_DEVICE_TABLE, values, idCol + " = ?",
                new String[] { String.valueOf(model.getId()) });
    }
//    public void deleteSingleModel(SQLTestModel model) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
//                new String[] { String.valueOf(model.get_id()) });
//        db.close();
//    }
}
