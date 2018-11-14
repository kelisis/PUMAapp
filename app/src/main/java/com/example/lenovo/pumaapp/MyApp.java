package com.example.lenovo.pumaapp;

import android.app.Application;
import android.bluetooth.BluetoothSocket;

/**
 * Created by lenovo on 2018/5/30.
 */

public class MyApp extends Application {
    public BluetoothSocket name;

    public BluetoothSocket getName() {
        return name;
    }

    public void setName(BluetoothSocket name) {
        this.name = name;
    }
}
