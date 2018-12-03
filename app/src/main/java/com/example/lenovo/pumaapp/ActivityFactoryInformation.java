package com.example.lenovo.pumaapp;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by lenovo on 2018/12/2.
 */

public class ActivityFactoryInformation extends AppCompatActivity{

    public static BluetoothSocket btSocket;
    String toSend = "";
    private ImageButton button_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factoryinformation);

        //蓝牙getName()
        //BluetoothSocket btSocket  = ((MyApp)getApplication()).getName();

        button_close=(ImageButton)findViewById(R.id.button_close);

        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
