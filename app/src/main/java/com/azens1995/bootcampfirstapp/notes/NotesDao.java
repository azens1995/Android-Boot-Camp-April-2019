package com.azens1995.bootcampfirstapp.notes;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Azens Eklak on 17/4/19.
 * Ishani Technology Pvt. Ltd
 * azens1995@gmail.com
 */

@Dao
public interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addNote(Notes notes);

    @Update
    void updateNote(Notes notes);

    @Query("SELECT * FROM notes")
    LiveData<List<Notes>> getAllNotes();

    @Query("DELETE FROM notes")
    void deleteAllNotes();

}
