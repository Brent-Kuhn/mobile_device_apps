package edu.floridapoly.cop4656.spring19.kuhn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import edu.floridapoly.cop4656.spring19.kuhn.RecyclerTouchListener;

public class MainActivity extends AppCompatActivity {
    private NotesAdapter mAdapter;
    private List<Note> notesList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noNotesView;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.notesList);
        coordinatorLayout = findViewById(R.id.coordinator_layout);

        // Get all
        db = new DatabaseHelper(this);
        notesList.addAll(db.getAllNotes());

        mAdapter = new NotesAdapter(this, notesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, NoteActivity.class), 0);
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
            }

            @Override
            public void onLongClick(View view, int position) {
                Intent i = new Intent(MainActivity.this, EditNoteActivity.class);
                String positionString = String.valueOf(position);
                Bundle extras = new Bundle();
                extras.putInt("position", position);
                extras.putString("note", notesList.get(position).getNote());
                i.putExtras(extras);
                startActivityForResult(i, 0);
                Log.v("position", notesList.get(position).getNote());

            }
        }));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if(resultCode != Activity.RESULT_OK) { return;}

        if(resultCode == 0) {
            long id = data.getExtras().getLong("new_note_id");
            Note n = db.getNote(id);
            if (n != null) {
                // adding new note to array list at 0 position
                notesList.add(0, n);

                // refreshing the list
                mAdapter.notifyDataSetChanged();
            }
        }

        if (resultCode == 1) {
            int position = data.getExtras().getInt("position");
            String note = data.getExtras().getString("note");
            Note n = notesList.get(position);
            n.setNote(note);

            db.updateNote(n);

            notesList.set(position, n);
            mAdapter.notifyItemChanged(position);

        }

        if (resultCode == 2) {
            int position = data.getExtras().getInt("position");
            // deleting the note from db
            db.deleteNote(notesList.get(position));

            // removing the note from the list
            notesList.remove(position);
            mAdapter.notifyItemRemoved(position);

            // refreshing the list
            mAdapter.notifyDataSetChanged();
        }

    }


}
