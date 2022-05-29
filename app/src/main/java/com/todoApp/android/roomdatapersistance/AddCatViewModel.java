package com.todoApp.android.roomdatapersistance;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.todoApp.android.roomdatapersistance.database.AppDatabase;
import com.todoApp.android.roomdatapersistance.database.CategoryEntry;
import com.todoApp.android.roomdatapersistance.database.CategoryRepository;

public class AddCatViewModel extends ViewModel {
    // COMPLETED (6) Add a category member variable for the CategoryEntry object wrapped in a LiveData
    private LiveData<CategoryEntry> cat;
    private final CategoryRepository categoryRepository;

    // COMPLETED (8) Create a constructor where you call loadCatById of the CatDao to initialize the cat variable
    // Note: The constructor should receive the database and the catId
    public AddCatViewModel(AppDatabase database, int catId) {
        // task = database.taskDao().loadTaskById(catId);
        categoryRepository = new CategoryRepository(database);
        cat = categoryRepository.getloadCategoryById(catId);
    }

    // COMPLETED (7) Create a getter for the cat variable
    public LiveData<CategoryEntry> getCat() {
        return cat;
    }

    public void updateCat(CategoryEntry cat) {
        categoryRepository.updateCategoryById(cat);
    }
}
