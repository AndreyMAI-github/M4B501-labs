package mai.skvortsovim.texteditor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView notepad;
    Button makeToastBtn;
    TextView fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notepad = findViewById(R.id.notepadEditText);
        makeToastBtn = findViewById(R.id.showTextBtn);
        fileName = findViewById(R.id.fileNameText);

        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            case R.id.action_list:
                    Toast.makeText(getApplicationContext(),"List", Toast.LENGTH_SHORT).show();
                    getFileNames();
                break;
        }
        return super.onOptionsItemSelected(item);
    };

    public void saveOutTxt(){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(fileName.getText().toString(),MODE_PRIVATE);
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
            fileInputStream = openFileInput(fileName.getText().toString());
            byte[] data = new byte[fileInputStream.available()];
            fileInputStream.read(data);
            notepad.setText(new String(data));
            Toast.makeText(getApplicationContext(),"Loaded File", Toast.LENGTH_SHORT).show();
        }catch (Exception exception){
            Toast tmp = Toast.makeText(getApplicationContext(), exception.toString(), Toast.LENGTH_SHORT);
            tmp.show();
        }
    };

    public void getFileNames(){
        File filePath = this.getFilesDir();
        String filePathString = filePath.toString();
        File fileDir = new File(filePathString);
        ArrayList<String> fileNames = new ArrayList<String>();
        for (File f : fileDir.listFiles()) {
                fileNames.add(f.getName());
        }

        notepad.setText("Files \nFileName:\n" + fileNames.toString().replaceAll("\\[|\\]",""));

    };

}