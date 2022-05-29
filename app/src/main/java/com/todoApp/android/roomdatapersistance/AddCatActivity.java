package com.todoApp.android.roomdatapersistance;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.todoApp.android.roomdatapersistance.database.AppDatabase;
import com.todoApp.android.roomdatapersistance.database.AppExecutors;
import com.todoApp.android.roomdatapersistance.database.CategoryEntry;

import java.util.Date;


public class AddCatActivity extends AppCompatActivity {
    // Extra for the cat ID to be received in the intent
    public static final String EXTRA_CAT_ID = "extraCatId";
    // Extra for the cat ID to be received after rotation
    public static final String INSTANCE_CAT_ID = "instanceCatId";
    // Constant for default cat id to be used when not in update mode
    private static final int DEFAULT_CAT_ID = -1;
    // Constant for logging
    private static final String TAG = AddCatActivity.class.getSimpleName();
    // Fields for views
    EditText mEditCat;
    Button mButton;

    private int mCatId = DEFAULT_CAT_ID;

    // Member variable for the Database
    private AppDatabase mDb;
    AddCatViewModel viewModel;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cat);

        initViews();

        mDb = AppDatabase.getInstance(getApplicationContext());

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_CAT_ID)) {
            mCatId = savedInstanceState.getInt(INSTANCE_CAT_ID, DEFAULT_CAT_ID);
        }

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_CAT_ID)) {
            mButton.setText(R.string.update_button);
            if (mCatId == DEFAULT_CAT_ID) {
                mCatId = intent.getIntExtra(EXTRA_CAT_ID, DEFAULT_CAT_ID);
                AddCatViewModelFactory factory = new AddCatViewModelFactory(mDb, mCatId);
                viewModel = ViewModelProviders.of(this, factory).get(AddCatViewModel.class);
                viewModel.getCat().observe(this, new Observer<CategoryEntry>() {
                    @Override
                    public void onChanged(CategoryEntry catEntry) {
                        viewModel.getCat().removeObserver(this);
                        populateUI(catEntry);
                    }
                });
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(INSTANCE_CAT_ID, mCatId);
        super.onSaveInstanceState(outState);
    }

    /**
     * initViews is called from onCreate to init the member variable views
     */
    private void initViews() {
        mEditCat = findViewById(R.id.editTextCatName);
        mButton = findViewById(R.id.saveButtonCat);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveButtonClicked();
            }
        });
    }

    /**
     * populateUI would be called to populate the UI when in update mode
     *
     * @param cat the taskEntry to populate the UI
     */
    private void populateUI(CategoryEntry cat) {
        // COMPLETED (7) return if the task is null
        if (cat == null) {
            return;
        }

        // COMPLETED (8) use the variable cat to populate the UI
        mEditCat.setText(cat.getCatName());
    }

    /**
     * onSaveButtonClicked is called when the "save" button is clicked.
     * It retrieves user input and inserts that new task data into the underlying database.
     */
    public void onSaveButtonClicked() {
        String name = mEditCat.getText().toString();
        Date dates = new Date();
        Log.d("CatName: ",name);

        final CategoryEntry categoryEntry = new CategoryEntry(name);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                    mDb.categoryDao().insertCategory(categoryEntry);
                finish();
            }
        });
    }
}
