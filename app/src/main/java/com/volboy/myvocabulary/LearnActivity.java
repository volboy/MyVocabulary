package com.volboy.myvocabulary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LearnActivity extends AppCompatActivity {

    public static final String END_LEARN="END_LEARN";
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

    }
    public void onClick(View view){
        String myStr=getIntent().getStringExtra(MainActivity.USER_NAME);
        Intent intent=new Intent();
        intent.putExtra(END_LEARN, myStr);
        setResult(RESULT_OK, intent);
        finish();
    }
}
