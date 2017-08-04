package comeaa.workout;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by ErinA on 7/25/2017.
 */

public class ListAdapter extends ArrayAdapter {
    ListContent exerciseContent = new ListContent(this.getContext());

    ArrayList<ExerciseItem> myList = new ArrayList<ExerciseItem>();
    final DbHelper myDbHelper = DbHelper.getInstance(this.getContext());
    final SQLiteDatabase db = myDbHelper.getWritableDatabase();
    final Cursor cursor = db.rawQuery("select * from " + DbHelper.FeedEntry.TABLE_NAME, null);
    Context context; //initialize context
    public String exerciseName;


    public ListAdapter(Context context, ArrayList<ExerciseItem> exerciseList) { //takes in the list you want to display
        super(context, R.layout.list_view_row, exerciseList);
        this.context = context; //set context
        myList = exerciseList;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) { //goes through list and displays it
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.list_view_row, parent, false);
        convertView.setHapticFeedbackEnabled(true);

        final TextView name = (TextView) convertView.findViewById(R.id.Exercise); //set the values in the row of the list
        final TextView reps = (TextView) convertView.findViewById(R.id.reps);
        final TextView weight = (TextView) convertView.findViewById(R.id.weight);
        final TextView max = (TextView) convertView.findViewById(R.id.max);
        final TextView sets = (TextView) convertView.findViewById(R.id.sets);
        //final CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
        exerciseName = myList.get(position).getName();
        name.setText(exerciseName);
        sets.setText("Sets: " + myList.get(position).getSets());
        reps.setText("Reps: " + myList.get(position).getReps());
        weight.setText("Weight: " + myList.get(position).getWeight());
        max.setText("Max: " + myList.get(position).getMax());


        Intent editWorkoutIntent = new Intent(context, EditMyWorkout.class);
        editWorkoutIntent.putExtra("exerciseList", myList);
        editWorkoutIntent.putExtra("position", position);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //response to clicking name
                Intent editWorkoutIntent = new Intent(getContext(), EditMyWorkout.class);
                editWorkoutIntent.putExtra("exerciseList", myList);
                editWorkoutIntent.putExtra("position", position);
                getContext().startActivity(editWorkoutIntent);
            }
        });

        return convertView;


    }
}

