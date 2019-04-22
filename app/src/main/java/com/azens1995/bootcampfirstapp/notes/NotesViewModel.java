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
public class NotesViewModel extends AndroidViewModel {

    private static final String TAG = "NotesViewModel";

    private LiveData<List<Notes>> mAllNotes;
    private NotesDatabase notesDatabase;
    private NotesDao notesDao;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        notesDatabase = NotesDatabase.getInstance(application);
        notesDao = notesDatabase.notesDao();
        mAllNotes = notesDao.getAllNotes();
    }

    public LiveData<List<Notes>> getmAllNotes() {
        return mAllNotes;
    }

    public void addNotes(Notes notes){
        new insetAsyncTask(notesDatabase).execute(notes);
    }

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
