package com.todoApp.android.roomdatapersistance.database;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class CategoryEntry {

    @PrimaryKey(autoGenerate = true)
    private int catId;

    private String catName;
    @Ignore
    public CategoryEntry(String catName) {
        this.catName = catName;
    }

    public CategoryEntry(int catId, String catName) {
        this.catId = catId;
        this.catName = catName;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
