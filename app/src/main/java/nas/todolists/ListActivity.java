package nas.todolists;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Sander de Wijs on 29-11-2016.
 */

public class ListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // show the third activity
        setContentView(R.layout.tasklist_layout);

        // create and perform intent to show completed story
        Intent receiveTasklist = getIntent();
        TextView taskTV = (TextView) findViewById(R.id.);
    }

}
