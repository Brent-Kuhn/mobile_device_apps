package edu.floridapoly.cop4656.spring19.kuhn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Note extends AppCompatActivity {
    private TextView dateTimeText;
    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        dateTimeText = findViewById(R.id.dateTimeText);
        dateTimeText.setText(date);
    }
}
