package com.azens1995.bootcampfirstapp.employee;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Azens Eklak on 20/4/19.
 * Ishani Technology Pvt. Ltd
 * azens1995@gmail.com
 */

/*
* This is the Main Step 2. The first Step is to create the Entity/ POJO class
* */

/*
* Step 1: Annotate the interface with '@Dao' to make it Dao class
* Step 2: CRUD Operations
*       2.1 INSERT: To insert Data items in the Database,
*           Create a method which returns void and takes the Entity or List of Entity as parameter
*           Annotate the method with '@Insert'
*       2.2 RETRIEVE:
*           Create a method that returns Entity/ List of Entity and takes no parameter/ parameter as per situation
*           Annotate the method with '@Query' and write the sql query in the same annotation within the small braces
*       2.3 Update: Similar as Insert but change the annotation to '@Update'
*       2.4 DELETE: You can either user '@Delete' annotation or '@Query' annotation with the query to delete data/ list of data
* */
@Dao
public interface EmployeeDao {

    /*
    *
    * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllEmployees(List<Employee> employeeList);

    @Query("SELECT * FROM employee_table")
    LiveData<List<Employee>> getAllEmployees();
}
