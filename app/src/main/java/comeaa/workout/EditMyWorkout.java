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

public class EditMyWorkout extends AppCompatActivity {


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
        setContentView(R.layout.activity_edit_my_workout);
        android.support.v7.widget.Toolbar editWorkoutToolbar =
                (android.support.v7.widget.Toolbar) findViewById(R.id.editWorkoutToolbar);
        setSupportActionBar(editWorkoutToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        final Context context = this;
        final ListContent exerciseContent = new ListContent(this);

        final DbHelper myDbHelper = DbHelper.getInstance(this.getApplicationContext());
        final SQLiteDatabase db = myDbHelper.getWritableDatabase();

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        final ArrayList<ExerciseItem> exerciseList = (ArrayList<ExerciseItem>) getIntent().getSerializableExtra("exerciseList");
        final int i = intent.getIntExtra("position", 0);
        final ExerciseItem myExerciseItem = exerciseList.get(i);

        final EditText exerciseName = (EditText)findViewById(R.id.editText5);
        exerciseName.setText(myExerciseItem.getName()); //set what is already in the list

        final EditText sets = (EditText)findViewById(R.id.editText3);
        sets.setText(myExerciseItem.getSets()); //set what is already in the list

        final EditText reps = (EditText)findViewById(R.id.editText4);
        reps.setText(myExerciseItem.getReps()); //set what already exists

        final EditText weight = (EditText)findViewById(R.id.editText6);
        weight.setText(myExerciseItem.getWeight()); //set what already exists

        final EditText max = (EditText)findViewById(R.id.editText2);
        max.setText(myExerciseItem.getMax()); //set what already exists as the field

        Button addButton = (Button)findViewById(R.id.button5);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){ //update the values of the objects
                myExerciseItem.setName(exerciseName.getText().toString());
                myExerciseItem.setSets(sets.getText().toString());
                myExerciseItem.setReps(reps.getText().toString());
                myExerciseItem.setWeight(weight.getText().toString());
                myExerciseItem.setMax(max.getText().toString());

                exerciseContent.updateDB(db, myDbHelper, exerciseList, "workouts");

                Toast.makeText(context, myExerciseItem.getName() + " has been updated" , Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }
}
