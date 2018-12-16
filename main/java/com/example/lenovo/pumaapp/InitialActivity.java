package com.example.lenovo.pumaapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/6/17.
 */

public class InitialActivity extends AppCompatActivity {

    /*
    boolean bRun = true;
    private InputStream is;
    private String smsg = "";    //显示用数据缓存
    private String fmsg = "";    //保存用数据缓存
    public static BluetoothSocket btSocket;
    private BluetoothAdapter bluetoothAdapter;
    private ArrayAdapter<String> deviceAdapter;
    private List<String> listDevices;
    private ListView listView;
    private TextView back;
    private Button openBT;
    private Button searchBT;
    //private ImageButton button_closebluetooth;
    private MyApp myApp;
    int i = 0;
    String toSend = "";
    final private static int MESSAGE_READ = 100;
    */

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);


        listView = (ListView) this.findViewById(R.id.list);
        openBT = (Button) findViewById(R.id.open_btn);
        searchBT = (Button) findViewById(R.id.search_btn);
        //button_closebluetooth = (ImageButton) findViewById(R.id.button_closebluetooth);
        listDevices = new ArrayList<String>();
        back = (TextView) findViewById(R.id.back);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter.isEnabled()) {
            openBT.setText("关闭蓝牙");
        }

        deviceAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item, listDevices);
        listView.setAdapter(deviceAdapter);
        listView.setOnItemClickListener(new ItemClickListener());//添加监听
        openBT.setOnClickListener(new BTListener());
        searchBT.setOnClickListener(new BTListener());
        back.setOnClickListener(new BTListener());
        //BluetoothSocket btSocket  = ((MyApp)getApplication()).getName();
        */


    //this is not initially in the program!!
        /*
        button_closebluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        button_closebluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(InitialActivity.this,MainActivity.class);
                startActivity(a);
            }
        });

    }
    */

    /*
    private BroadcastReceiver receiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //搜索设备时，取得设备的MAC地址
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String str = device.getName() + "|" + device.getAddress();
                if (listDevices.indexOf(str) == -1)// 防止重复添加
                    listDevices.add(str); // 获取设备名称和mac地址
                if (deviceAdapter != null) {
                    deviceAdapter.notifyDataSetChanged();
                }
            }
        }
    };

    class BTListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.open_btn) {
                if (!bluetoothAdapter.isEnabled()) {
                    bluetoothAdapter.enable();//开启蓝牙
                    Intent enable = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    enable.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300); //300秒为蓝牙设备可见时间
                    startActivity(enable);
                    openBT.setText("关闭蓝牙");

                } else {
                    bluetoothAdapter.disable();//关闭蓝牙
                    openBT.setText("开启蓝牙");
                    if (btSocket != null) {
                        try {
                            btSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else if (view.getId() == R.id.search_btn) {
                if (!bluetoothAdapter.isEnabled()) {
                    Toast.makeText(getApplicationContext(), "请先开启蓝牙", Toast.LENGTH_SHORT).show();
                } else {
                    if (listDevices != null) {
                        listDevices.clear();
                        if (deviceAdapter != null) {
                            deviceAdapter.notifyDataSetChanged();
                        }
                    }
                    bluetoothAdapter.startDiscovery();
                    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                    registerReceiver(receiver, filter);

                }
            }
            else if(view.getId() == R.id.back){
                finish();
            }
        }
    }

    class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            if (!bluetoothAdapter.isEnabled()) {
                Toast.makeText(getApplicationContext(), "请先开启蓝牙", Toast.LENGTH_SHORT).show();
            } else {
                bluetoothAdapter.cancelDiscovery();//停止搜索
                String str = listDevices.get(position);
                String macAdress = str.split("\\|")[1];

                BluetoothDevice device = bluetoothAdapter.getRemoteDevice(macAdress);
                try {
                    Method clientMethod = device.getClass()
                            .getMethod("createRfcommSocket", new Class[]{int.class});
                    btSocket = (BluetoothSocket) clientMethod.invoke(device, 1);
                    myApp = (MyApp)getApplication();
                    myApp.setName(btSocket);
                    connect(btSocket);//连接设备


                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

                //此处返回主页面
                Intent intent = new Intent(InitialActivity.this,MainActivity.class);

                startActivity(intent);
            }
        }
    }

    public void connect(final BluetoothSocket btSocket) {
        Thread ReadThread=new Thread(){

            public void run(){
                int num = 0;
                byte[] buffer = new byte[1024];
                byte[] buffer_new = new byte[1024];
                int i = 0;
                int n = 0;
                bRun = true;
                //接收线程
                while(true){
                    try{
                        while(is.available()==0){
                            while(bRun == false){}
                        }
                        while(true){
                            num = is.read(buffer);         //读入数据
                            n=0;

                            String s0 = new String(buffer,0,num);
                            fmsg+=s0;    //保存收到数据
                            for(i=0;i<num;i++){
                                if((buffer[i] == 0x0d)&&(buffer[i+1]==0x0a)){
                                    buffer_new[n] = 0x0a;
                                    i++;
                                }else{
                                    buffer_new[n] = buffer[i];
                                }
                                n++;
                            }
                            String s = new String(buffer_new,0,n);
                            smsg+=s;   //写入接收缓存
                            if(is.available()==0)break;  //短时间没有数据才跳出进行显示
                        }
                        //发送显示消息，进行显示刷新
                        handler.sendMessage(handler.obtainMessage());
                    }catch(IOException e){
                    }
                }
            }
        };

        try {
            btSocket.connect();//连接
            if (btSocket.isConnected()) {
                Log.e("----connect--- :", "连接成功");
                Toast.makeText(getApplicationContext(), "蓝牙连接成功", Toast.LENGTH_SHORT).show();
                listView.setVisibility(View.GONE);
                //btContent.setVisibility(View.VISIBLE);
                try{
                    is = btSocket.getInputStream();   //得到蓝牙数据输入流
                    ReadThread.start();
                }catch(IOException e){
                    Toast.makeText(this, "接收数据失败！", Toast.LENGTH_SHORT).show();
                }
                //new ConnectThread().start();//通信

            } else {
                Toast.makeText(getApplicationContext(), "蓝牙连接失败", Toast.LENGTH_SHORT).show();
                btSocket.close();
                listView.setVisibility(View.VISIBLE);
                //btContent.setVisibility(View.GONE);
                Log.e("--------- :", "连接关闭");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    Handler handler= new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            //btAllData.setText(smsg);   //显示数据
            //sv.scrollTo(0,dis.getMeasuredHeight()); //跳至数据最后一页
        }
    };
    */


    //this is not initially in the program!
    //原始发送数据线程
    /*
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
    */

    /*
    public class SendThread extends Thread{
        public void run(){
            int i1=0;
            int n=0;
            try{
                BluetoothSocket btSocket  = ((MyApp)getApplication()).getName();
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
    */


    public static BluetoothSocket btSocket;
    private BluetoothAdapter bluetoothAdapter;
    private ArrayAdapter<String> deviceAdapter;
    private List<String> listDevices;
    private ListView listView;
    private LinearLayout btContent;
    private TextView btAllData;
    private Button openBT;
    private Button searchBT;
    private Button send;
    private Button opButton;
    private Button stButton;
    private EditText edit0;
    private TextView tv;
    String toSend = "";
    String toSend1 = "";
    final private static int MESSAGE_READ = 100;
    static int X = 6;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        listView = (ListView) this.findViewById(R.id.list);
        btContent = (LinearLayout) findViewById(R.id.bt_content_llt);

        openBT = (Button) findViewById(R.id.open_btn);
        searchBT = (Button) findViewById(R.id.search_btn);
        send = (Button) findViewById(R.id.send);
        opButton = (Button) findViewById(R.id.opButton);
        stButton = (Button) findViewById(R.id.stButton);
        edit0 = (EditText) findViewById(R.id.edittext);
        tv = (TextView) findViewById(R.id.tv);
        btAllData = (TextView) findViewById(R.id.all_data);

        listDevices = new ArrayList<String>();
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter.isEnabled()) {
            openBT.setText("关闭蓝牙");
        }
        deviceAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_item, listDevices);

        openBT.setOnClickListener(new BTListener());
        searchBT.setOnClickListener(new BTListener());
        send.setOnClickListener(new BTListener());
        opButton.setOnClickListener(new BTListener());
        stButton.setOnClickListener(new BTListener());

        listView.setAdapter(deviceAdapter);
        listView.setOnItemClickListener(new ItemClickListener());//添加监听
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            //搜索设备时，取得设备的MAC地址
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String str = device.getName() + "|" + device.getAddress();
                if (listDevices.indexOf(str) == -1)// 防止重复添加
                    listDevices.add(str); // 获取设备名称和mac地址
                if (deviceAdapter != null) {
                    deviceAdapter.notifyDataSetChanged();
                }
            }
        }
    };

    class BTListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.open_btn) {
                if (!bluetoothAdapter.isEnabled()) {
                    bluetoothAdapter.enable();//开启蓝牙
                    Intent enable = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    enable.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300); //300秒为蓝牙设备可见时间
                    startActivity(enable);
                    openBT.setText("关闭蓝牙");

                } else {
                    bluetoothAdapter.disable();//关闭蓝牙
                    openBT.setText("开启蓝牙");
                    if (btSocket != null) {
                        try {
                            btSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else if (view.getId() == R.id.search_btn) {
                if (!bluetoothAdapter.isEnabled()) {
                    Toast.makeText(getApplicationContext(), "请先开启蓝牙", Toast.LENGTH_SHORT).show();
                } else {
                    btContent.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    if (listDevices != null) {
                        listDevices.clear();
                        if (deviceAdapter != null) {
                            deviceAdapter.notifyDataSetChanged();
                        }
                    }
                    bluetoothAdapter.startDiscovery();
                    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                    registerReceiver(receiver, filter);

                }
            }
            else if (view.getId() == R.id.send){
                if (!bluetoothAdapter.isEnabled()) {
                    Toast.makeText(getApplicationContext(), "请先开启蓝牙", Toast.LENGTH_SHORT).show();
                } else {
                    toSend = edit0.getText().toString();
                    SendThread st = new SendThread();
                    st.start();
                    String ab = edit0.getText().toString();
                    Toast.makeText(getApplicationContext(), ab, Toast.LENGTH_SHORT).show();
                    edit0.setText("");
                }
            }
            else if (view.getId() == R.id.opButton){
                if(X == 7){
                    Toast.makeText(InitialActivity.this,"已经打开",Toast.LENGTH_SHORT).show();
                }
                else {
                    toSend = "P3K00E0E00F";
                    new SendThread().start();
                    X = 7;
                }
            }
            else if (view.getId() == R.id.stButton){
                if(X == 6){
                    Toast.makeText(InitialActivity.this,"已经关闭",Toast.LENGTH_SHORT).show();
                }
                else{
                    toSend = "P3G00E0E00F";
                    new SendThread().start();
                    X = 6;
                }
            }
        }
    }

    class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            if (!bluetoothAdapter.isEnabled()) {
                Toast.makeText(getApplicationContext(), "请先开启蓝牙", Toast.LENGTH_SHORT).show();
            } else {
                bluetoothAdapter.cancelDiscovery();//停止搜索
                String str = listDevices.get(position);
                String macAdress = str.split("\\|")[1];

                BluetoothDevice device = bluetoothAdapter.getRemoteDevice(macAdress);
                try {
                    Method clientMethod = device.getClass()
                            .getMethod("createRfcommSocket", new Class[]{int.class});
                    btSocket = (BluetoothSocket) clientMethod.invoke(device, 1);
                    connect(btSocket);//连接设备


                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void connect(final BluetoothSocket btSocket) {
        try {
            btSocket.connect();//连接
            if (btSocket.isConnected()) {
                Log.e("----connect--- :", "连接成功");
                Toast.makeText(getApplicationContext(), "蓝牙连接成功", Toast.LENGTH_SHORT).show();
                listView.setVisibility(View.GONE);
                btContent.setVisibility(View.VISIBLE);
                new ConnectThread().start();//通信
                ((MyApp)getApplication()).setName(btSocket);

            } else {
                Toast.makeText(getApplicationContext(), "蓝牙连接失败", Toast.LENGTH_SHORT).show();
                btSocket.close();
                listView.setVisibility(View.VISIBLE);
                btContent.setVisibility(View.GONE);
                Log.e("--------- :", "连接关闭");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class ConnectThread extends Thread {
        public void run() {
            try {
                InputStream inputStream = btSocket.getInputStream();
                byte[] data = new byte[1024];
                int len = 0;
                String result = "";

                while (len != -1) {
                    if (inputStream.available() > 0 == false) {//inputStream接收的数据是一段段的，如果不先
                        continue;
                    } else {
                        try {
                            Thread.sleep(500);//等待0.5秒，让数据接收完整
                            len = inputStream.read(data);
                            result = URLDecoder.decode(new String(data, "utf-8"));
                            Log.e("----result：----- :", ">>>" + result);
                            Message msg = new Message();
                            msg.what = MESSAGE_READ;
                            msg.obj = result;
                            handler.sendMessage(msg);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                inputStream.close();//关不了，也好像不能关
                Log.e("--------- :", "关闭inputStream");
                if (btSocket != null) {
                    btSocket.close();
                }
            } catch (IOException e) {
                Log.e("TAG", e.toString());
            }
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_READ:
                    String result = (String) msg.obj;
                    String data = result.split("\\r\\n")[0];
                    Log.e("----data：----- :", ">>>" + data);
                    if (i < 6) {
                        Editable text = (Editable) btAllData.getText();
                        text.append(data);
                        btAllData.setText(text + "\r\n");
                        i++;
                    } else {
                        btAllData.setText(data + "\r\n");
                        i = 0;
                    }
                    break;
            }
        }
    };

    //发送数据线程
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


    public class Send1Thread extends Thread{
        public void run(){
            int i1=0;
            int n=0;
            try{
                OutputStream os = btSocket.getOutputStream();   //蓝牙连接输出流
                byte[] bos = toSend1.getBytes();
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


    //以下为数据改变函数
    Handler TimeHandler = new Handler();
    Runnable OpenRunnable = new Runnable() {
        @Override
        public void run() {
            String A = String.valueOf(X/10);
            String B = String.valueOf(X%10);
            String str1 = "P3";
            String str4 = "P2";
            String str2 = "0";
            String str3 = "E0E00F";
            String FinalString = str1 + A + str2 + B + str3;
            String FinalString1 = str4 + A + str2 + B + str3;
            toSend1 = FinalString1;
            toSend = FinalString;
            Toast.makeText(InitialActivity.this, FinalString, Toast.LENGTH_SHORT).show();
            X = X + 1;
            new SendThread().start();
            new Send1Thread().start();

            TimeHandler.postDelayed(OpenRunnable,600);
        }
    };

    Runnable ShutRunnable = new Runnable() {
        @Override
        public void run() {
            X = X - 1;
            String A = String.valueOf(X/10);
            String B = String.valueOf(X%10);
            String str1 = "P3";
            String str2 = "0";
            String str3 = "E0E00F";
            String str4 = "P2";
            String FinalString = str1 + A + str2 + B + str3;
            String FinalString1 = str4 + A + str2 + B + str3;
            toSend = FinalString;
            toSend1 = FinalString1;
            Toast.makeText(InitialActivity.this, FinalString, Toast.LENGTH_SHORT).show();
            new SendThread().start();
            new Send1Thread().start();

            TimeHandler.postDelayed(ShutRunnable,600);
        }
    };

    Runnable RemoveRunnable = new Runnable() {
        @Override
        public void run() {
            TimeHandler.removeCallbacks(OpenRunnable);

        }
    };

    Runnable RemoveShutRunnable = new Runnable() {
        @Override
        public void run() {
            TimeHandler.removeCallbacks(ShutRunnable);

        }
    };


    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
