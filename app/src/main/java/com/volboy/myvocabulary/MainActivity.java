package com.volboy.myvocabulary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    public static final  String USER_NAME="com.volboy.myvocabulary.USER_NAME"; //ключ для  intent AddActivity
    private static final int REQUEST_CODE=0; //для intent LearnActivity
    String user="Иванов_Иван";
    TextView txtWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtWelcome=findViewById(R.id.textWelcome);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //получаем индентификатор выбранного пункта меню
        int id=item.getItemId();

        switch (id){
            case R.id.mode_one:
                txtWelcome.setText("Прямой перевод");
                item.setChecked(true);
                return true;

            case R.id.mode_two:
                txtWelcome.setText("Обратный перевод");
                item.setChecked(true);
                return true;
            case R.id.mode_three:
                txtWelcome.setText("Случайно");
                item.setChecked(true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    public void onClick(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.btnAdd:
                intent=new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra(USER_NAME, user);
                startActivity(intent);
                break;
            case R.id.btnLearn:
                intent=new Intent(MainActivity.this, LearnActivity.class);
                intent.putExtra(USER_NAME, user);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.button:
                intent=new Intent(MainActivity.this, ExperimentActivity.class);
                startActivity(intent);
                break;
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE){
            if (resultCode==RESULT_OK){
                txtWelcome.setText(data.getStringExtra(LearnActivity.END_LEARN));
            }
            else{
                txtWelcome.setText("Ошибка");
            }
        }
    }
}
