package comeaa.workout;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddWorkout extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);
        android.support.v7.widget.Toolbar addWorkoutToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.addWorkoutToolbar);
        setSupportActionBar(addWorkoutToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        final Context context = this;

        final ListContent exerciseContent = new ListContent(this);
        DbHelper myDbHelper = DbHelper.getInstance(this.getApplicationContext());
        final SQLiteDatabase db = myDbHelper.getWritableDatabase();

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        final ArrayList<ExerciseItem> exerciseList = (ArrayList<ExerciseItem>) getIntent().getSerializableExtra("exerciseList");
        final ExerciseItem myExerciseItem;

        final EditText exerciseName = (EditText)findViewById(R.id.editText5);
        final EditText sets = (EditText)findViewById(R.id.editText3);
        final EditText reps = (EditText)findViewById(R.id.editText4);
        final EditText weight = (EditText)findViewById(R.id.editText6);
        final EditText max = (EditText)findViewById(R.id.editText2);

        Button addButton = (Button)findViewById(R.id.button5);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                DbHelper myDbHelper = DbHelper.getInstance(context);
                SQLiteDatabase db = myDbHelper.getWritableDatabase();
                exerciseContent.addListItem(exerciseList,exerciseName.getText().toString(), sets.getText().toString(),
                        reps.getText().toString(), weight.getText().toString(),max.getText().toString());

                exerciseContent.updateDB(db, myDbHelper, exerciseList, "workouts");

                //Intent bicepsTricepsIntent = new Intent(context, BicepsTriceps.class);
                //editWorkoutIntent.putExtra("exerciseList", myList);
                //editWorkoutIntent.putExtra("position", position);
                //startActivity(bicepsTricepsIntent);
                finish();
                //this.finish();

            }
        });
    }
}
