package com.example.lenovo.pumaapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ActivityCommunity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        Intent intent = new Intent(Intent.ACTION_VIEW);    //为Intent设置Action属性
        intent.setData(Uri.parse("http://jalopnik.com")); //为Intent设置DATA属性
        startActivity(intent);
    }
}
