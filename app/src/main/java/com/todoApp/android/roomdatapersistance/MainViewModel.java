package com.todoApp.android.roomdatapersistance;

import android.app.Application;
import android.util.Log;
import com.todoApp.android.roomdatapersistance.database.AppDatabase;
import com.todoApp.android.roomdatapersistance.database.CategoryEntry;
import com.todoApp.android.roomdatapersistance.database.CategoryRepository;
import com.todoApp.android.roomdatapersistance.database.TaskEntry;
import com.todoApp.android.roomdatapersistance.database.TasksRepository;

import java.util.List;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainViewModel extends AndroidViewModel {

    // Constant for logging
    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<TaskEntry>> tasks;
    private LiveData<List<CategoryEntry>> cats;
    private final TasksRepository tasksRepository;
    private final CategoryRepository categoryRepository;
    public MainViewModel(Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving the tasks and category from the DataBase");
        tasksRepository = new TasksRepository(database);
        tasks = tasksRepository.getloadAllTasks();
        categoryRepository = new CategoryRepository(database);
        cats = categoryRepository.getloadAllCategories();
    }

    public LiveData<List<TaskEntry>> getTasks() {
        return tasks;
    }

    public LiveData<List<CategoryEntry>> getCat() {
        return cats;
    }

    public void deleteTask(TaskEntry taskEntry) {
        tasksRepository.deleteTasks(taskEntry);
    }
    public void deleteCat(CategoryEntry catEntry) {
        categoryRepository.deleteCategories(catEntry);
    }
}