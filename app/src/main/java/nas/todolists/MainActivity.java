package nas.todolists;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SuperUsefulString manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tasksListView = (ListView) findViewById(R.id.tasks_listview);
        showTasks();
        manager = SuperUsefulString.getInstance();
        TextView stringTv = (TextView) findViewById(R.id.firstStringTV);
        stringTv.setText(manager.getSuperUsefulString());
    }

    public void setString(View view) {
        EditText et = (EditText) findViewById(R.id.setStringet);
        String newString = et.getText().toString();

            // deze regel is niet meer nodig, wordt in onCreate gefixt
        //SuperUsefulString manager = SuperUsefulString.getInstance();
        manager.setSuperUsefulString(newString);

        Intent intent = new Intent(this, ListActivity, );
        //

    }

    ListView tasksListView;

    public class TasksAdapter extends CursorAdapter {

        public TasksAdapter(Context context, Cursor cursor) {
            super(context, cursor, 0);
        }

        // The newView method is used to inflate a new view and return it,
        // you don't bind any data to the view at this point.
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.listview_textview_layout, parent, false);
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

    public void AddTaskToList(View view) {
        // create variable of edittext with id add_task_et
        EditText taskEditText = (EditText) findViewById(R.id.add_task_et);

        DBhelper dbHelp = new DBhelper(this);

        if (taskEditText.getText().length() != 0) {
            // convert entered words to separate strings
            String taskString = taskEditText.getText().toString();

            dbHelp = new DBhelper(this);
            dbHelp.create(taskString);
            dbHelp.close();
        }
        // show error message when no task is entered
        else {
            taskEditText.setError("Please enter a task");
        }
        // replace entered word with nothing, so textbox is emptied
        taskEditText.setText("");
        showTasks();
    }

    public void showTasks() {
        final DBhelper dbHelp = new DBhelper(this);
        Cursor cursor = dbHelp.read();

        final TasksAdapter tasksAdapter = new TasksAdapter(this, cursor);
        tasksListView.setAdapter(tasksAdapter);

        dbHelp.close();

        tasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Object taskObject = tasksListView.getItemAtPosition(position);
//                Cursor cursor2 = (Cursor) tasksListView.getItemAtPosition(position);
//                String taskString = cursor2.getString(cursor2.getColumnIndexOrThrow("task"));
//
//                TextView task = (TextView) findViewById(R.id.task_textview);
//                task.setPaintFlags(task.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                String item = ((TextView)view).getText().toString();



                //dbHelp = new DBhelper(this);
                dbHelp.update(item);
                dbHelp.close();

                // show toast to inform user of checking task
                Toast.makeText(MainActivity.this, "Task done!", Toast.LENGTH_SHORT).show();
            }
        });


    tasksListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
            TextView task = (TextView) findViewById(R.id.task_textview);



            LinearLayout linearLayoutTV = (LinearLayout) findViewById(R.id.linearlayout_textview);
            linearLayoutTV.removeView(task);

            //String taskString = TextView.getText().toString();
            final int positionToRemove = position;
            dbHelp.delete(positionToRemove);
            dbHelp.close();

            // show toast to inform user of deletion
            Toast.makeText(MainActivity.this, "Task deleted, position " + position, Toast.LENGTH_SHORT).show();

            return true;
            }
        });
    }

    // switch from current activity to list activity, which shows the chosen tasklist
    public void switchToListActivity(View view) {
        Intent tasklist_layout_intent = new Intent(this, ListActivity.class);
        startActivity(tasklist_layout_intent);
    }


    /*in onCreate van class UpdateActivity
    * SuperUsefulStringManager manager = ...
    * TextView stringtv = (TextView) findviewbyid.R.id.newSuperString);
    * manager.getSuperUsefulString()
    * string.setText(manger.getSuperUsefulString());
    *
    */

}
