package com.azens1995.bootcampfirstapp.notes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Azens Eklak on 17/4/19.
 * Ishani Technology Pvt. Ltd
 * azens1995@gmail.com
 */
@Entity(tableName = "notes")
public class Notes implements Serializable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String note;

    public Notes(int id, String note) {
        this.id = id;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", note='" + note + '\'' +
                '}';
    }
}
