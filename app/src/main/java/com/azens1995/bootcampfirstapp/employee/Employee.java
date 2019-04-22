package com.azens1995.bootcampfirstapp.employee;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Azens Eklak on 20/4/19.
 * Ishani Technology Pvt. Ltd
 * azens1995@gmail.com
 */

/*
* This is Step 1. The second step is to create the Dao which is interface
* */

/*
* Step 1: Annotate the class with '@Entity', and give the table name within the small braces
*
* Some shortcuts to use: (may be not applicable to all keyboards)
* After creating all variables, to add getters and setters, user "ALT+INSERT" and choose "Getter and Setter"
* from the options. Similar for creating a constructor
* */
//Step 1
@Entity(tableName = "employee_table")
public class Employee {

    //Step 2, Annotate a primary key value
    @NonNull
    @PrimaryKey
    private int id;
    private String name;
    private String username;
    private String email;
    private String website;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
