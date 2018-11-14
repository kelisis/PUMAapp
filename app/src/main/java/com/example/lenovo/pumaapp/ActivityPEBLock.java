package com.example.lenovo.pumaapp;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;

import static com.example.lenovo.pumaapp.R.attr.lock_tips_tx;

/**
 * Created by lenovo on 2018/6/1.
 */

public class ActivityPEBLock extends AppCompatActivity {

    //private SlideUnlockView slideUnlockView;
    //private Vibrator vibrator;
    public static BluetoothSocket btSocket;
    String toSend = "";
    private String mTipText;
    private ImageView car_shadow;
    private SlideLockView slide1;
    private ImageButton button_PEBLockBack;
    private int n=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peblock);

        //蓝牙getName()
        btSocket  = ((MyApp)getApplication()).getName();

        car_shadow=(ImageView)findViewById(R.id.CarShadow);
        slide1=(SlideLockView)findViewById(R.id.slideLockView);
        button_PEBLockBack=(ImageButton)findViewById(R.id.button_PEBLockBack);

        slide1.setmLockListener(new SlideLockView.OnLockListener() {
            @Override
            public void onOpenLockSuccess() {
                if(n==0)                                                 //n=0表示车辆没有锁，此时滑动滑块将车锁住
                {
                    //toSend="LOCK";
                    car_shadow.setVisibility(View.VISIBLE);
                    slide1.setUnlock();
                    n=1;

                    //try and see if this works
                    toSend = "LOCK";
                    SendThread st = new SendThread();
                    st.start();
                    //end
                }
                else{                                                    //n=1表示车辆锁住了，
                    //toSend="UNLOCK";
                    car_shadow.setVisibility(View.INVISIBLE);
                    slide1.setLock();
                    n=0;

                    //try and see if this works
                    toSend = "UNLOCK";
                    SendThread st = new SendThread();
                    st.start();
                    //end
                }
            }
        });

        button_PEBLockBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        button_PEBLockBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 获取系统振动器服务
        //vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        // 初始化控件

        //slideUnlockView = (SlideUnlockView) findViewById(R.id.slideUnlockView);
        //imageView=(ImageView) findViewById(R.id.button_LockCar);


        // 设置滑动解锁-解锁的监听
        /*slideUnlockView.setOnUnLockListener(new SlideUnlockView.OnUnLockListener() {
            @Override
            public void setUnLocked(boolean unLock) {
                // 如果是true，证明解锁
                if (unLock) {
                    // 启动震动器 100ms
                    //vibrator.vibrate(100);
                    // 当解锁的时候，执行逻辑操作，在这里仅仅是将图片进行展示
                    imageView.setVisibility(View.VISIBLE);
                    // 重置一下滑动解锁的控件
                    slideUnlockView.reset();
                    // 让滑动解锁控件消失
                    slideUnlockView.setVisibility(View.GONE);
                }
            }
        });*/

    }

    //add these definitions and see if it works
    public class SendThread extends Thread{
        public void run(){
            int i1=0;
            int n=0;
            try{
                OutputStream os = btSocket.getOutputStream();   //蓝牙连接输出流
                byte[] bos = toSend.getBytes();
                //byte [] bos = s.getBytes();
                for(i1=0;i1<bos.length;i1++){
                    if(bos[i1]==0x0a)n++;
                }
                byte[] bos_new = new byte[bos.length+n];
                n=0;
                for(i1=0;i1<bos.length;i1++){ //手机中换行为0a,将其改为0d 0a后再发送
                    if(bos[i1]==0x0a){
                        bos_new[n]=0x0d;
                        n++;
                        bos_new[n]=0x0a;
                    }else{
                        bos_new[n]=bos[i1];
                    }
                    n++;
                }

                os.write(bos_new);
            }
            catch(IOException e){
            }
        }
    }
}
