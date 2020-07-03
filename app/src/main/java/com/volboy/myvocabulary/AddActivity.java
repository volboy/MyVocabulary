package com.volboy.myvocabulary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        textView=findViewById(R.id.textView_about_content);
        String myStr=getIntent().getStringExtra(MainActivity.USER_NAME);
        textView.setText(textView.getText().toString()+" "+myStr);
    }
}
