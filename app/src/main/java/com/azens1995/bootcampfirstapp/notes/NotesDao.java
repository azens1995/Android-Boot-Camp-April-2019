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

/*
* Step 1: Annotate the interface with @Dao
* Step 2: Make methods to Add note. Annotate the method with @Insert to insert the data in the sqlite table
* Step 3: Updating the data. Similar as insert.
* Step 4: Make a method to get all the data from the table. We are using sql query to retrive the data using @Query() annotation
*       This method returns the List<Notes> and we are warapping list of notes inside the LiveData<> to observe the data changes
*
* Step 5: Method to delete all notes from the database. We are using @Query to add the sql query to delete all data from the table
* */

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
