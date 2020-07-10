package com.volboy.myvocabulary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class NoteActivity extends AppCompatActivity {
    public static final String FILENAME="note.txt";
    EditText editText;

    //создание активити
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        editText=findViewById(R.id.editText);
    }

    //активити на переднем плане
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences mPref= PreferenceManager.getDefaultSharedPreferences(this);
        //читаем установленное значение из CheckBoxPreferences
        if (mPref.getBoolean(getString(R.string.pref_openmode), false)){
            openFile(FILENAME);
        }
        //читаем установленное значение из EditTextPreferences
        float textSize=Float.parseFloat(mPref.getString(getString(R.string.pref_size), "20"));
        editText.setTextSize(textSize);

        //читаем установленный стиль шрифта из ListPreferences
        String regular=mPref.getString(getString(R.string.pref_style), "");
        int typeFace= Typeface.NORMAL;
        if (regular.contains("Полужирный")){
            typeFace=+Typeface.BOLD_ITALIC;
        }
        if (regular.contains("Курсив")){
            typeFace=+Typeface.ITALIC;
        }
        if (regular.contains("Жирный")){
            typeFace=+Typeface.BOLD;
        }
        editText.setTypeface(null, typeFace);
    }

    //создание опционального меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //обработка выбора пункта меню
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.note_open:
                openFile(FILENAME);
                return true;
            case R.id.note_save:
                saveFile(FILENAME);
                return true;
            case R.id.note_settings:
                Intent intent=new Intent(NoteActivity.this, mPreferenceActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //функция открытия файла
    private void openFile(String fileName){
        StringBuilder builder = null;
        try {
            InputStream inputStream=openFileInput(fileName);
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String line;
            builder=new StringBuilder();
            while ((line=bufferedReader.readLine())!=null){
                builder.append(line + "\n");
            }
            inputStream.close();
            editText.setText(builder.toString());

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error" + e.getMessage(),Toast.LENGTH_LONG).show();
        }
        finally {

        }
    }

    //функция сохранения файла
    private void saveFile(String fileName){
        try{
            OutputStream outputStream=openFileOutput(fileName, 0);
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(outputStream);
            outputStreamWriter.write(editText.getText().toString());
            outputStreamWriter.close();

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_LONG).show();

        }
        finally {


        }
    }
}
