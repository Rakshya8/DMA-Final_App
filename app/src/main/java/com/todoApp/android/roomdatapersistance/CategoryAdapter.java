package com.todoApp.android.roomdatapersistance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.todoApp.android.roomdatapersistance.database.CategoryEntry;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * This TaskAdapter creates and binds ViewHolders, that hold the description and priority of a task,
 * to a RecyclerView to efficiently display data.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    // Constant for date format
    private static final String DATE_FORMAT = "dd/MM/yyy";

    // Class variables for the List that holds task data and the Context
    private List<CategoryEntry> mCatEntries;
    private Context mContext;
    // Date formatter
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    /**
     * Constructor for the TaskAdapter that initializes the Context.
     *
     * @param context  the current Context
     */
    public CategoryAdapter(Context context) {
        mContext = context;
    }

    /**
     * Called when ViewHolders are created to fill a RecyclerView.
     *
     * @return A new TaskViewHolder that holds the view for each task
     */
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         //Inflate the task_layout to a view
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.cat_layout, parent, false);

        return new CategoryAdapter.CategoryViewHolder(view);
    }

    /**
     * Called by the RecyclerView to display data at a specified position in the Cursor.
     *
     * @param holder   The ViewHolder to bind Cursor data to
     * @param position The position of the data in the Cursor
     */
    @Override
    public void onBindViewHolder(CategoryAdapter.CategoryViewHolder holder, int position) {
        // Determine the values of the wanted data
        CategoryEntry categoryEntry = mCatEntries.get(position);
        String catName = categoryEntry.getCatName();

        //Set values
        holder.catView.setText(catName);

    }

    /**
     * Returns the number of items to display.
     */
    @Override
    public int getItemCount() {
        if (mCatEntries == null) {
            return 0;
        }
        return mCatEntries.size();
    }

    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */
    public void setCats(List<CategoryEntry> catEntries) {
        mCatEntries = catEntries;
        notifyDataSetChanged();
    }

    public List<CategoryEntry> getCat() {
        return mCatEntries;
    }


    // Inner class for creating ViewHolders
    class CategoryViewHolder extends RecyclerView.ViewHolder {

        // Class variables for the task description and priority TextViews
        TextView catView;

        /**
         * Constructor for the TaskViewHolders.
         *
         * @param itemView The view inflated in onCreateViewHolder
         */
        public CategoryViewHolder(View itemView) {
            super(itemView);

            catView = itemView.findViewById(R.id.catTitle);
        }
    }
}
