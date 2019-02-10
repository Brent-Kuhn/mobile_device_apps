package kuhn.spring19.cop4656.floridapoly.edu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.app.Activity;

import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button mNoteButton;
    private static final int NOTE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNoteButton = (Button) findViewById(R.id.note_button);
        mNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent i = new Intent(MainActivity.this, Notes.class);
                i.putExtra("myName", getResources().getString(R.string.my_name));

                startActivityForResult(i, NOTE);
            }

        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != Activity.RESULT_OK) { return;}

        if(resultCode == RESULT_OK) {
            Toast.makeText(MainActivity.this, data.getExtras().getString("note_text"), Toast.LENGTH_SHORT).show();
        }

    }
}
