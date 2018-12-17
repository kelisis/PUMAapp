package com.example.lenovo.pumaapp;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by lenovo on 2018/6/1.
 */

public class ActivityInformation extends AppCompatActivity {

    public static BluetoothSocket btSocket;
    String toSend = "";
    private ImageButton button_Mileage;
    private ImageButton button_AbnormalRecords;
    private ImageButton button_FactoryInformation;
    private ImageButton button_PEBBinding;
    private ImageButton information_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        //蓝牙getName()
        //BluetoothSocket btSocket  = ((MyApp)getApplication()).getName();

        //绑定控件
        button_Mileage=(ImageButton)findViewById(R.id.button_Mileage);
        button_AbnormalRecords=(ImageButton)findViewById(R.id.button_AbnormalRecords);
        button_FactoryInformation=(ImageButton)findViewById(R.id.button_FactoryInformation);
        button_PEBBinding=(ImageButton)findViewById(R.id.button_PEBBinding);
        information_back=(ImageButton)findViewById(R.id.information_back);

        //为控件添加事件
        button_Mileage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        button_Mileage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(ActivityInformation.this,ActivityMileage.class);
                startActivity(a);
            }
        });

        button_AbnormalRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        button_AbnormalRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(ActivityInformation.this,ActivityAbnormalRecords.class);
                startActivity(a);
            }
        });

        button_FactoryInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        button_FactoryInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(ActivityInformation.this,ActivityFactoryInformation.class);
                startActivity(a);
            }
        });

        button_PEBBinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        button_PEBBinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(ActivityInformation.this,ActivityPEBBinding.class);
                startActivity(a);
            }
        });



        information_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        information_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
