package nas.todolists;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Sander de Wijs on 27-11-2016.
 */

public class TasksAdapter extends CursorAdapter {
    public TasksAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.activity_main, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView taskTextView = (TextView) view.findViewById(R.id.task_textview);
        // Extract properties from cursor
        String taskString = cursor.getString(cursor.getColumnIndexOrThrow("task"));
        // Populate fields with extracted properties
        taskTextView.setText(taskString);
    }
}