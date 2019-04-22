package com.azens1995.bootcampfirstapp;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.azens1995.bootcampfirstapp.notes.Notes;
import com.azens1995.bootcampfirstapp.notes.NotesActivity;
import com.azens1995.bootcampfirstapp.notes.NotesViewModel;

public class AddNotesActivity extends AppCompatActivity {
    private EditText edtNote;
    private Button btnNote;
    private NotesViewModel notesViewModel;
    Notes noteIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        edtNote = findViewById(R.id.text_notes_add);
        btnNote = findViewById(R.id.btn_notes_save);


        noteIntent = (Notes) getIntent().getSerializableExtra("NOTES_KEY");
        if (noteIntent != null){
            edtNote.setText(noteIntent.getNote());
            btnNote.setText("Update");
        }

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        btnNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the new notes
                if (!edtNote.getText().toString().isEmpty()){
                    String note = edtNote.getText().toString();

                    // update the old notes
                    if (noteIntent != null){
                        notesViewModel.updateNotes(new Notes(noteIntent.getId(),note));
                    }else {
                        notesViewModel.addNotes(new Notes(0,note));
                    }

                    Intent intent = new Intent(AddNotesActivity.this, NotesActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}
