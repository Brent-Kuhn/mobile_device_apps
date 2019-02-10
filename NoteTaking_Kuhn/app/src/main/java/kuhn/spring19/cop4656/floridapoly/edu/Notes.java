package kuhn.spring19.cop4656.floridapoly.edu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Notes extends AppCompatActivity {

    private String message;
    private String Hello = "Hello ";
    private String EnterNote = ", please enter your note";
    private Button mCancelButton;
    private Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        Intent i = getIntent();
        message = Hello.concat(i.getStringExtra("myName").concat(EnterNote));

        final EditText Note = findViewById(R.id.editText);
        Note.setText(message);

        mCancelButton = (Button) findViewById(R.id.cancel_button);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                finish();
            }
        });

        mSaveButton = (Button) findViewById(R.id.save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                String FinalNote = Note.getText().toString();
                if (FinalNote.equals("")) {
                    Toast.makeText(Notes.this, getResources().getString(R.string.nothing), Toast.LENGTH_SHORT).show();
                } else {
                    Intent data = new Intent();
                    data.putExtra("note_text", FinalNote);

                    setResult(RESULT_OK, data);
                    finish();
                }
//                Toast.makeText(Notes.this, FinalNote, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
