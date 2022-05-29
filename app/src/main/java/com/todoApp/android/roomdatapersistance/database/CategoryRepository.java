package com.todoApp.android.roomdatapersistance.database;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CategoryRepository {
    private static final String LOG_TAG = TasksRepository.class.getSimpleName();
    private LiveData<List<CategoryEntry>> categories;
    private CategoryDao categoryDao;
    AppDatabase database;
    public CategoryRepository(AppDatabase database) {
        this.database = database;
    }


    public LiveData<List<CategoryEntry>> getloadAllCategories() {
        categories = database.categoryDao().loadAllCategory();
        return categories;
    }


    public LiveData<CategoryEntry> getloadCategoryById(int catId) {
        return database.categoryDao().loadCategoryById(catId);
    }

    public void deleteCategories(CategoryEntry categoryEntry) {
        database.categoryDao().deleteCategory(categoryEntry);
    }

    public void updateCategoryById(CategoryEntry categoryEntry) {
        database.categoryDao().updateCategory(categoryEntry);
    }
}
