package com.todoApp.android.roomdatapersistance.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM categories")
    LiveData<List<CategoryEntry>> loadAllCategory();

    @Query("SELECT * FROM categories WHERE catId=:id")
    LiveData<CategoryEntry> loadCategoryById(int id);

    @Insert
    void insertCategory(CategoryEntry categoryEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCategory(CategoryEntry categoryEntry);

    @Delete
    void deleteCategory(CategoryEntry categoryEntry);

    @Query("select catName from categories where catId=:catId")
    String findCatName(final int catId);

    @Query ("select catId from categories where catName=:catName")
    Integer findCatId(final String catName);

    @Query("SELECT catName FROM categories")
    List<String> getAllName();
}
