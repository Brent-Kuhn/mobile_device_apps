package edu.floridapoly.cop4656.spring19.kuhn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class NoteActivity extends AppCompatActivity {
    private TextView dateTimeText;
    private Button mCancelButton;
    private Button mSaveButton;
    private DatabaseHelper db;

    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        db = new DatabaseHelper(this);

        dateTimeText = findViewById(R.id.dateTimeText);
        dateTimeText.setText(date);

        mCancelButton = findViewById(R.id.cancel);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(-1);
                finish();
            }
        });

        mSaveButton = findViewById(R.id.save);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText note = findViewById(R.id.note);
                long id = db.insertNote(note.getText().toString());

                Note n = db.getNote(id);

                Intent data = new Intent();
                data.putExtra("new_note_id", id);

                setResult(0, data);

                Toast.makeText(getApplicationContext(), n.getNote(), Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }
}
