package edu.floridapoly.cop4656.spring19.kuhn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class EditNoteActivity extends AppCompatActivity {
    private TextView dateTimeText;
    private Button mCancelButton;
    private Button mEditButton;
    private Button mDeleteButton;
    private DatabaseHelper db;
    private TextView noteText;
    private int position;
    private String note;

    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        Intent i = getIntent();

        db = new DatabaseHelper(this);

        Bundle extras = i.getExtras();

        position = extras.getInt("position");
        note = extras.getString("note");


        dateTimeText = findViewById(R.id.dateTimeText);
        dateTimeText.setText(date);

        noteText = findViewById(R.id.note);
        noteText.setText(note);

        mCancelButton = findViewById(R.id.cancel);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(-1);
                finish();
            }
        });

        mEditButton = findViewById(R.id.edit);
        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String editNote = noteText.getText().toString();

                Intent data = new Intent();
                data.putExtra("note", editNote);
                data.putExtra("position", position);

                setResult(1, data);

                Toast.makeText(getApplicationContext(), editNote, Toast.LENGTH_SHORT).show();

                finish();
            }
        });

        mDeleteButton = findViewById(R.id.delete);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("position", position);

                setResult(2, data);

                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }
}
