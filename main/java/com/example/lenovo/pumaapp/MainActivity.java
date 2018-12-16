package com.example.lenovo.pumaapp;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    //定义控件及蓝牙
    public static BluetoothSocket btSocket;
    String toSend = "";
    private ImageButton button_PEBLock;
    private ImageButton button_Information;
    private ImageButton button_Location;
    private ImageButton button_Settings;
    private ImageButton button_bluetooth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //蓝牙getName()
        btSocket = ((MyApp) getApplication()).getName();
        /*        BluetoothSocket btSocket  = ((MyApp)getApplication()).getName();*/

        //绑定控件
        button_PEBLock = (ImageButton) findViewById(R.id.button_PEBLock);
        button_Information = (ImageButton) findViewById(R.id.button_Information);
        button_Location = (ImageButton) findViewById(R.id.button_Location);
        button_Settings = (ImageButton) findViewById(R.id.button_Settings);
        button_bluetooth = (ImageButton) findViewById(R.id.button_bluetooth);

        toSend = "P5G00E0E00F";

        //为控件添加事件

/*        button_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/

        button_PEBLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        button_PEBLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, ActivityPEBLock.class);
                startActivity(a);
            }
        });

        button_Information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        button_Information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d = new Intent(MainActivity.this, ActivityInformation.class);
                startActivity(d);
            }
        });

        button_Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        button_Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(MainActivity.this, ActivityLocation.class);
                startActivity(b);
            }
        });

        button_Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        button_Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e = new Intent(MainActivity.this, ActivitySettings.class);
                startActivity(e);
            }
        });

        button_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        button_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, InitialActivity.class);
                startActivity(a);
            }
        });
    }
}
