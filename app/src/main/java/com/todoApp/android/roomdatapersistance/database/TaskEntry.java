package com.todoApp.android.roomdatapersistance.database;


import static androidx.room.ForeignKey.CASCADE;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "task",
        foreignKeys = @ForeignKey(entity = CategoryEntry.class,
        parentColumns = "catId",
        childColumns = "catId",
        onDelete = CASCADE))
public class TaskEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private int priority;
    private String description;
    private int taskDate;
    @ColumnInfo(name = "catId",index = true)
    private int catId;
    private String taskMonth;
    private int taskYear;

    // COMPLETED (1) Make updatedAt match a column named updated_at. Tip: Use the ColumnInfo annotation
    @ColumnInfo(name = "updated_at")
    private Date updatedAt;

    @Ignore
    public TaskEntry(String name, int priority, Date updatedAt, String description,  int taskDate, String taskMonth, int taskYear, int catId) {
        this.description = description;
        this.priority = priority;
        this.updatedAt = updatedAt;
        this.name = name;
        this.catId=catId;
        this.taskYear=taskYear;
        this.taskMonth=taskMonth;
        this.taskDate=taskDate;
    }

    public TaskEntry(int id, String name, int priority, Date updatedAt, String description, int catId, int taskDate, String taskMonth, int taskYear) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.updatedAt = updatedAt;
        this.name = name;
        this.catId=catId;
        this.taskYear=taskYear;
        this.taskMonth=taskMonth;
        this.taskDate=taskDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTaskMonth() {
        return taskMonth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public void setTaskMonth(String taskMonth) {
        this.taskMonth = taskMonth;
    }

    public int getTaskYear() {
        return taskYear;
    }

    public void setTaskYear(int taskYear) {
        this.taskYear = taskYear;
    }

    public int getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(int taskDate) {
        this.taskDate = taskDate;
    }
}
