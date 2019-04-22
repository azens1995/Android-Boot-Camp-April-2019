package com.azens1995.bootcampfirstapp.notes;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.azens1995.bootcampfirstapp.employee.Employee;
import com.azens1995.bootcampfirstapp.employee.EmployeeDao;

/**
 * Created by Azens Eklak on 17/4/19.
 * Ishani Technology Pvt. Ltd
 * azens1995@gmail.com
 */

/*
* Main Step 3. The previous one was to create the Dao Interface
* This must be a single class. There should be only one database and many entities(which represents tables)
* */

/*
* Step 1: Annotate the class with '@Database'. Open the small braces and within it add-> entites, version, and exportSchema
* Step 2: Make the class abstract, and extend the class with RoomDatabase
* */

    //step 1: Annotation
    //step 2: Making the class abstract, and extend the RoomDatabase
@Database(entities = {Notes.class, Employee.class}, version = 1, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {
    //step 3: Creating a static variable for the given database class
    private static NotesDatabase INSTANCE;

    //step 4: Add all your Daos here
    public abstract NotesDao notesDao();
    public abstract EmployeeDao employeeDao();

    // step 5: Make a static method that returns the instance of the given database class
    public static NotesDatabase getInstance(Context context){
        //check if the instance is null
        if (INSTANCE == null){
            synchronized (NotesDatabase.class){
                if (INSTANCE == null){
                    // get the new instance of the room database if the instance was null
                    // Room.databaseBuilder('the first is the context','second parameter is the Database class', 'third is database name')
                    INSTANCE = Room.databaseBuilder(context,
                            NotesDatabase.class, "notes_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        //step 4: return the database instance
        return INSTANCE;

    }
}
