package com.example.lenovo.pumaapp;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.widget.TextView;

/**
 * Created by lenovo on 2018/12/9.
 */

public class TextMileage extends Activity {

    public TextView m_TextView1;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mileage);

        m_TextView1 = (TextView) findViewById(R.id.mileage);

        String str1 = "数字";
        m_TextView1.setText(str1);
    }
}
