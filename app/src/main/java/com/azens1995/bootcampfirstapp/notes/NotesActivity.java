package com.azens1995.bootcampfirstapp.notes;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.azens1995.bootcampfirstapp.AddNotesActivity;
import com.azens1995.bootcampfirstapp.R;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;
    private NotesViewModel notesViewModel;
    private List<Notes> allNotes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.notes_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
        notesAdapter = new NotesAdapter(this);
        recyclerView.setAdapter(notesAdapter);

        notesViewModel.getmAllNotes().observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(@Nullable List<Notes> notes) {
                notesAdapter.addNotes(notes);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent to open the Add Note activity when floating button is clicked
                Intent intent = new Intent(NotesActivity.this, AddNotesActivity.class);
                startActivity(intent);
            }
        });
    }

    // Menu inflation
    /*
    * To create menu, first make the xml file in the menu folder.
    * Create a folder in res folder. Name the folder "menu". Note that the naming should be all in small letters
    * Add an Xml and name it note_menu. Please view the code for more understanding and read the developer notes
    *
    * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // This is similar as using LayoutInflater in Recyclerview
        // The change is we are inflating menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_menu, menu);
        return true;
    }

    // handling click in the menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_delete:
                // We are calling deleteAllNotes method from the viewmodel to delete all the methods when the menu item is clicked
                notesViewModel.deleteAllNotes();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
