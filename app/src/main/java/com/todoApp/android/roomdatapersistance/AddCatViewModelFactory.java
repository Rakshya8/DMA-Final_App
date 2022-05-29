package com.todoApp.android.roomdatapersistance;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.todoApp.android.roomdatapersistance.database.AppDatabase;

public class AddCatViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    // COMPLETED (2) Add two member variables. One for the database and one for the taskId
    private final AppDatabase mDb;
    private final int mCatId;

    // COMPLETED (3) Initialize the member variables in the constructor with the parameters received
    public AddCatViewModelFactory(AppDatabase database, int catId) {
        mDb = database;
        mCatId = catId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new AddCatViewModel(mDb, mCatId);
    }
}

