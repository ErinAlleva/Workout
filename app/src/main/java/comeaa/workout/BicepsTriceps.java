package comeaa.workout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.ArrayList;

public class BicepsTriceps extends AppCompatActivity {
    private static final String TAG = "BicepsTriceps";
    ListContent myListContent = new ListContent(this);
    ListView lv;
    ListAdapter adapter;
    private static final String savedThingsKey = "savedThings";
    ArrayList<ExerciseItem>  exerciseList = new ArrayList<ExerciseItem>();
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_biceps_triceps);

        android.support.v7.widget.Toolbar bicepsTricepsToolbar =
                (android.support.v7.widget.Toolbar) findViewById(R.id.biceptsTricepsToolbar);
        setSupportActionBar(bicepsTricepsToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //database visualizer on chrome://inspect
        Stetho.newInitializerBuilder(this).enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(
                        Stetho.defaultInspectorModulesProvider(this))
                .build();
        Stetho.initializeWithDefaults(this);
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        //initialize and create db
        DbHelper myDbHelper = DbHelper.getInstance(context);
        final SQLiteDatabase db = myDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " +DbHelper.FeedEntry.TABLE_NAME, null);
        ListContent myContent = new ListContent(context);



       if (savedInstanceState==null && cursor.moveToFirst()) {
            exerciseList = myContent.readDB( db, cursor, exerciseList);
            myContent.updateDB(db, myDbHelper, exerciseList, "workouts");
        } else if (cursor.moveToFirst()) {
            myContent.updateDB(db, myDbHelper, exerciseList, "workouts");
        } else {
            exerciseList = myContent.populateArray(exerciseList);
            myContent.insertThings(db, exerciseList);
            exerciseList = myContent.readDB(db, cursor, exerciseList);
        }

        lv = (ListView) findViewById(R.id.bisTrisListView); //initialize listview for list of exercises
        adapter = new ListAdapter(this, exerciseList);
        lv.setAdapter(adapter);

    }

    public void addButtonTB(View view) {

        Intent addWorkoutIntent = new Intent(this, AddWorkout.class);
        addWorkoutIntent.putExtra("exerciseList", exerciseList);
        startActivity(addWorkoutIntent);
    }
    public void openBicepsDB(View view) {
        Intent bicepsDBIntent = new Intent (this, bicepsDB.class);
        startActivity(bicepsDBIntent);
    }
}





















































//opens dialog that adds a new exercise
   /* LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

final DbHelper myDbHelper = new DbHelper(context);
final SQLiteDatabase db = myDbHelper.getWritableDatabase();

final EditText nameEditText = new EditText(this);
        nameEditText.setHint("Exercise Name");
        layout.addView(nameEditText);
final EditText setsEditText = new EditText(this);
        nameEditText.setHint("Sets");
        layout.addView(setsEditText);
final EditText repsEditText = new EditText(this);
        repsEditText.setHint("Reps");
        layout.addView(repsEditText);
final EditText weightEditText = new EditText(this);
        weightEditText.setHint("Weight");
        layout.addView(weightEditText);
final EditText maxEditText = new EditText(this);
        maxEditText.setHint("Max");
        layout.addView(maxEditText);

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Edit Exercise Information");
        //.setMessage("What do you want to do next?");
        dialog.setView(layout);
        dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface dialog, int which) {
        String task = String.valueOf(nameEditText.getText());
        String sets = String.valueOf(setsEditText.getText());
        String weight = String.valueOf(weightEditText.getText());
        String reps = String.valueOf(repsEditText.getText());
        String max = String.valueOf(maxEditText.getText());
        myListContent.addListItem(exerciseList, sets, task, weight, reps, max); //add items to list for this page
        adapter.notifyDataSetChanged(); //notify adapter that the list has changed
        myListContent.updateDB(db, myDbHelper, exerciseList, "workouts"); //update the db with new entry
        }
        })
        .setNegativeButton("Cancel", null)
        .create();
        dialog.show();
*/