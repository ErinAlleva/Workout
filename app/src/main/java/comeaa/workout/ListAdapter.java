package comeaa.workout;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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
    ListContent exerciseContent = new ListContent(getContext());

    ArrayList<ExerciseItem> myList = new ArrayList<ExerciseItem>();
    final DbHelper myDbHelper = new DbHelper(getContext());
    final SQLiteDatabase db = myDbHelper.getWritableDatabase();
    final Cursor cursor = db.rawQuery("select * from " + DbHelper.FeedEntry.TABLE_NAME,null);
    Context context; //initialize context
    public String exerciseName;


    public ListAdapter(Context context, ArrayList<ExerciseItem> exerciseList){ //takes in the list you want to display
        super(context,R.layout.list_view_row, exerciseList);
        this.context=context; //set context
        myList = exerciseList;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){ //goes through list and displays it
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
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



        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //response to clicking name
                final EditText nameEditText = new EditText(context);
                nameEditText.setHint("Exercise Name");
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Edit name of Exercise");
                dialog.setView(nameEditText);
                dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(nameEditText.getText());
                        myList.get(position).setName(task); //update name of the list item
                        notifyDataSetChanged(); //notify adapter that the list has changed
                        exerciseContent.updateDB(db, myDbHelper, myList, "workouts"); //update the db with new entry
                    }
                })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });
        sets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //response to clicking name
                final EditText setsEditText = new EditText(context);
                setsEditText.setHint("sets");
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Edit number of sets for " + exerciseName);
                dialog.setView(setsEditText);
                dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sets = String.valueOf(setsEditText.getText());
                        myList.get(position).setSets(sets); //update name of the list item
                        notifyDataSetChanged(); //notify adapter that the list has changed
                        exerciseContent.updateDB(db, myDbHelper, myList, "workouts"); //update the db with new entry
                    }
                })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });
        reps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //response to clicking name
                final EditText repsEditText = new EditText(context);
                repsEditText.setHint("reps");
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Edit number of reps for " + exerciseName);
                dialog.setView(repsEditText);
                dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String reps = String.valueOf(repsEditText.getText());
                        myList.get(position).setReps(reps); //update name of the list item
                        notifyDataSetChanged(); //notify adapter that the list has changed
                        exerciseContent.updateDB(db, myDbHelper, myList, "workouts"); //update the db with new entry
                    }
                })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });
        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //response to clicking name
                final EditText weightEditText = new EditText(context);
                weightEditText.setHint("weight");
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Edit amount of weight for " + exerciseName);
                dialog.setView(weightEditText);
                dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String weight = String.valueOf(weightEditText.getText());
                        myList.get(position).setWeight(weight); //update name of the list item
                        notifyDataSetChanged(); //notify adapter that the list has changed
                        exerciseContent.updateDB(db, myDbHelper, myList, "workouts"); //update the db with new entry
                    }
                })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });
        max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //response to clicking name
                final EditText maxEditText = new EditText(context);
                maxEditText.setHint("max");
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Edit max weight for " + exerciseName);
                dialog.setView(maxEditText);
                dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String max = String.valueOf(maxEditText.getText());
                        myList.get(position).setMax(max); //update name of the list item
                        notifyDataSetChanged(); //notify adapter that the list has changed
                        exerciseContent.updateDB(db, myDbHelper, myList, "workouts"); //update the db with new entry
                    }
                })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });
        return convertView;
    }
}

