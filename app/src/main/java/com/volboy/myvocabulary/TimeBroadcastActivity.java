package com.volboy.myvocabulary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class TimeBroadcastActivity extends AppCompatActivity {

    TimeBroadcastReceiver timeBroadcastReceiver=new TimeBroadcastReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_broadcast);
    }

    //регистрируем шировещательный приемник
    //для намерения android.intent.action.TIME_TICK -
    //сообщение посылается каждую минуту
    public void registerBroadcastReceiver(View view){
        this.registerReceiver(timeBroadcastReceiver, new IntentFilter("android.intent.action.TIME_TICK"));
        Toast.makeText(this, "Приемник включен", Toast.LENGTH_SHORT).show();

    }

    public void unRegisterBroadcastReceiver(View view){
        this.unregisterReceiver(timeBroadcastReceiver);
        Toast.makeText(this, "Приемник отключен", Toast.LENGTH_SHORT).show();

    }
}
