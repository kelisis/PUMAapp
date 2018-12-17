package com.example.lenovo.pumaapp;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by lenovo on 2018/6/5.
 */

public class ActivityMileage extends AppCompatActivity {

    public static BluetoothSocket btSocket;
    String toSend = "";
    private ImageButton mileage_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mileage);

        //蓝牙getName()
        //BluetoothSocket btSocket  = ((MyApp)getApplication()).getName();

        mileage_back=(ImageButton)findViewById(R.id.mileage_back);

        mileage_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        mileage_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
