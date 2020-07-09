package com.volboy.myvocabulary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
            case R.id.button2:
                showPopupMenu(view);
                break;
            case R.id.button3:
                intent=new Intent(MainActivity.this, WebViewPage.class);
                startActivity(intent);
                break;
        }


    }

    private void showPopupMenu(View view){
        PopupMenu popupMenu=new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popupmenu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.pmode_one:
                        txtWelcome.setText("Режим один из всплывающего меню");
                        return true;
                    case R.id.pmode_two:
                        txtWelcome.setText("Режим два из всплывающего меню");
                        return true;
                    case R.id.pmode_three:
                        txtWelcome.setText("Режим три из всплывающего меню");
                        return true;
                    default:
                        return false;
                }

            }
        });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                Toast.makeText(getApplicationContext(), "onDismiss", Toast.LENGTH_LONG).show();
            }
        });
        popupMenu.show();
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

    private static long back_pressed;
    @Override
    public void onBackPressed(){
        //openQuitDialog();
        if (back_pressed + 2000>System.currentTimeMillis())
            super.onBackPressed();
        else
            Toast.makeText(MainActivity.this, "Нажмите Back еще раз для выхода", Toast.LENGTH_SHORT).show();
        back_pressed=System.currentTimeMillis();

    }

    private void openQuitDialog(){
        AlertDialog.Builder quitDialog=new AlertDialog.Builder(MainActivity.this);
        quitDialog.setTitle("Выход: Вы уверены?");

        quitDialog.setPositiveButton("Да", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                finish();
            }
        });
        quitDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){

            }
        });
        quitDialog.show();
    }
}

