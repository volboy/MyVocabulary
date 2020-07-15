package com.volboy.myvocabulary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        StringBuilder stringBuilder=new StringBuilder("Текущее время: ");
        Format mFormater=new SimpleDateFormat("hh:mm:ss a");
        stringBuilder.append(mFormater.format(new Date()));
        Toast.makeText(context, stringBuilder, Toast.LENGTH_LONG).show();


    }
}
