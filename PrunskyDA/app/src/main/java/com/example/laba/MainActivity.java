package com.example.laba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    TextView notepad;
    Button makeToastBtn;
    TextView iimya;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notepad = findViewById(R.id.notepadEditText);
        makeToastBtn = findViewById(R.id.showTextBtn);
        iimya = findViewById(R.id.imya);

        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick( View view) {
                Toast.makeText(getApplicationContext(),notepad.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        };

        makeToastBtn.setOnClickListener(btnListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                Toast.makeText(getApplicationContext(),"Save", Toast.LENGTH_SHORT).show();
                saveOutTxt();
                break;
            case R.id.action_load:
                Toast.makeText(getApplicationContext(),"Load", Toast.LENGTH_SHORT).show();
                loadOutTxt();
                break;
        }
        return super.onOptionsItemSelected(item);
    };

    public String iiimya(){
        if (iimya.getText().length() == 0) {
            return "bla_bla.txt";
        } else {
            return iimya.getText().toString()+".txt";
        }
    };
    public void saveOutTxt(){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(iiimya(),MODE_PRIVATE);
            fileOutputStream.write(notepad.getText().toString().getBytes(StandardCharsets.UTF_8));
            Toast tmp = Toast.makeText(getApplicationContext(),"File Saved", Toast.LENGTH_SHORT);
            tmp.show();
        }catch (Exception exception){
            Toast tmp = Toast.makeText(getApplicationContext(), exception.toString(), Toast.LENGTH_SHORT);
            tmp.show();
        }
    };

    public void loadOutTxt(){
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = openFileInput(iimya.getText().toString());
            byte[] data = new byte[fileInputStream.available()];
            fileInputStream.read(data);
            notepad.setText(new String(data));
            Toast.makeText(getApplicationContext(),"Loaded File", Toast.LENGTH_SHORT).show();
        }catch (Exception exception){
            Toast tmp = Toast.makeText(getApplicationContext(), exception.toString(), Toast.LENGTH_SHORT);
            tmp.show();
        }
    };
}