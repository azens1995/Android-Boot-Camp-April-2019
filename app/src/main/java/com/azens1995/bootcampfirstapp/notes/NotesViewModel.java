package com.azens1995.bootcampfirstapp.notes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

/**
 * Created by Azens Eklak on 17/4/19.
 * Ishani Technology Pvt. Ltd
 * azens1995@gmail.com
 */

/*
* Step 1: Extend the class with AndroidViewModel. Using Alt+Enter key, retrieve the constructor.
* Step 2: Make variable for NoteDatabase and NoteDao as well as LiveData<List<Notes>>.
*
* Always use public for all the methods that are to be used in the activity, like insert method, delete, and others.
* */
public class NotesViewModel extends AndroidViewModel {

    // This is TAG, used for logging purpose.
    private static final String TAG = "NotesViewModel";

    // Step 1: Variable declaration
    // this will hold the list of notes and using livedata gives it ability to observe the changes in the data
    private LiveData<List<Notes>> mAllNotes;
    private NotesDatabase notesDatabase;
    private NotesDao notesDao;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        // Step 2: Instantiation of the database
        notesDatabase = NotesDatabase.getInstance(application);
        notesDao = notesDatabase.notesDao();
        // This will retrive all the notes and assign it to the mAllNotes.
        // Note that mAllNotes is a LiveData<List<Notes>> variable and notesDao.getAllNotes() too returns the LiveData<List<notes>>
        mAllNotes = notesDao.getAllNotes();
    }

    // Method that will be used in the activity to get all the notes data and populate in the recyclerview
    public LiveData<List<Notes>> getmAllNotes() {
        return mAllNotes;
    }

    // This method will add the notes to the database
    public void addNotes(Notes notes){
        new insetAsyncTask(notesDatabase).execute(notes);
    }

    // This method will delete all the notes from the database.
    // Check activity for the usage of this method.

    public void updateNotes(Notes notes){
        new updateAsyncTask(notesDatabase).execute(notes);
    }

    public void deleteAllNotes(){
        //notesDatabase.notesDao().deleteAllNotes();
        new deleteAsyncTask(notesDatabase).execute();
    }

    private class insetAsyncTask extends AsyncTask<Notes, Void, Void> {
        NotesDatabase notesDatabase;

        public insetAsyncTask(NotesDatabase notesDatabase) {
            this.notesDatabase = notesDatabase;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            notesDatabase.notesDao().addNote(notes[0]);
            Log.d(TAG, "doInBackground: "+notes[0].toString());
            return null;
        }
    }

    private class updateAsyncTask extends AsyncTask<Notes, Void, Void>{
        private NotesDatabase database;
        public updateAsyncTask(NotesDatabase notesDatabase) {
            database = notesDatabase;
        }

        @Override
        protected Void doInBackground(Notes... notes) {
            database.notesDao().updateNote(notes[0]);
            return null;
        }
    }

    private class deleteAsyncTask extends AsyncTask<Void, Void, Void> {
        NotesDatabase database;
        public deleteAsyncTask(NotesDatabase notesDatabase) {
            database = notesDatabase;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.notesDao().deleteAllNotes();
            return null;
        }
    }
}
