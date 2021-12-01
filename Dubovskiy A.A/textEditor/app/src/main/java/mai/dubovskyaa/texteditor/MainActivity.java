package mai.dubovskyaa.texteditor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText notepad;
    Button makeToastBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notepad = findViewById(R.id.notepadEditText);
        makeToastBtn = findViewById(R.id.showTextBtn);

        View.OnClickListener btnListner = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast tmp = Toast.makeText(getApplicationContext(),notepad.getText().toString(),Toast.LENGTH_SHORT);
                tmp.show();
            }
        };


        makeToastBtn.setOnClickListener(btnListner);
    }
}