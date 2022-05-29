package com.todoApp.android.roomdatapersistance;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.todoApp.android.roomdatapersistance.database.AppDatabase;
import com.todoApp.android.roomdatapersistance.database.TaskEntry;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * This TaskAdapter creates and binds ViewHolders, that hold the description and priority of a task,
 * to a RecyclerView to efficiently display data.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    // Constant for date format
    private static final String DATE_FORMAT = "dd/MM/yyy";

    // Member variable to handle item clicks
    final private ItemClickListener mItemClickListener;
    // Class variables for the List that holds task data and the Context
    private List<TaskEntry> mTaskEntries;
    private int catId;
    private Context mContext;
    // Date formatter
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    /**
     * Constructor for the TaskAdapter that initializes the Context.
     *
     * @param context  the current Context
     * @param listener the ItemClickListener
     */
    public TaskAdapter(Context context, ItemClickListener listener) {
        mContext = context;
        mItemClickListener = listener;
    }

    /**
     * Called when ViewHolders are created to fill a RecyclerView.
     *
     * @return A new TaskViewHolder that holds the view for each task
     */
    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the task_layout to a view
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.task_layout, parent, false);

        return new TaskViewHolder(view);
    }

    /**
     * Called by the RecyclerView to display data at a specified position in the Cursor.
     *
     * @param holder   The ViewHolder to bind Cursor data to
     * @param position The position of the data in the Cursor
     */
    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        // Determine the values of the wanted data
        TaskEntry taskEntry = mTaskEntries.get(position);
        String description = taskEntry.getDescription();
        int priority = taskEntry.getPriority();
        String name= taskEntry.getName();
        String month = taskEntry.getTaskMonth();
        int date = taskEntry.getTaskDate();
        catId = taskEntry.getCatId();
        String updatedAt = dateFormat.format(taskEntry.getUpdatedAt());
        GetId gt = new GetId();
        String category = gt.doInBackground();
        Log.d("Cat", category);

        //Set values
        holder.taskDescriptionView.setText(description);
        holder.taskNameView.setText(name);
        holder.taskDateView.setText(String.valueOf(date));
        holder.taskMonthView.setText(month);
        holder.taskCat.setText(category);


        // Programmatically set the text and color for the priority TextView
        String priorityString = "" + priority; // converts int to String
        holder.priorityView.setText(priorityString);

        GradientDrawable priorityCircle = (GradientDrawable) holder.priorityView.getBackground();
        // Get the appropriate background color based on the priority
        int priorityColor = getPriorityColor(priority);
        priorityCircle.setColor(priorityColor);
    }

    /*
    Helper method for selecting the correct priority circle color.
    P1 = red, P2 = orange, P3 = yellow
    */
    private int getPriorityColor(int priority) {
        int priorityColor = 0;

        switch (priority) {
            case 1:
                priorityColor = ContextCompat.getColor(mContext, R.color.materialRed);
                break;
            case 2:
                priorityColor = ContextCompat.getColor(mContext, R.color.materialOrange);
                break;
            case 3:
                priorityColor = ContextCompat.getColor(mContext, R.color.materialYellow);
                break;
            default:
                break;
        }
        return priorityColor;
    }

    /**
     * Returns the number of items to display.
     */
    @Override
    public int getItemCount() {
        if (mTaskEntries == null) {
            return 0;
        }
        return mTaskEntries.size();
    }

    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */
    public void setTasks(List<TaskEntry> taskEntries) {
        mTaskEntries = taskEntries;
        notifyDataSetChanged();
    }

    public List<TaskEntry> getTasks() {
        return mTaskEntries;
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    // Inner class for creating ViewHolders
    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Class variables for the task description and priority TextViews
        TextView taskDescriptionView;
        TextView updatedAtView;
        TextView priorityView;
        TextView taskNameView;
        TextView taskDateView;
        TextView taskMonthView;
        TextView taskCat;

        /**
         * Constructor for the TaskViewHolders.
         *
         * @param itemView The view inflated in onCreateViewHolder
         */
        public TaskViewHolder(View itemView) {
            super(itemView);

            taskNameView = itemView.findViewById(R.id.taskDescription);
            taskDescriptionView = itemView.findViewById(R.id.editTaskDesc);
            taskDateView = itemView.findViewById(R.id.taskDateView);
            taskMonthView = itemView.findViewById(R.id.taskMonthView);

            priorityView = itemView.findViewById(R.id.priorityTextView);
            taskCat = itemView.findViewById(R.id.catId);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int elementId = mTaskEntries.get(getAdapterPosition()).getId();
            mItemClickListener.onItemClickListener(elementId);
        }

    }
    class GetId extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String cat = AppDatabase
                    .getInstance(mContext.getApplicationContext())
                    .categoryDao()
                    .findCatName(catId);
            return cat;
        }

    }
}