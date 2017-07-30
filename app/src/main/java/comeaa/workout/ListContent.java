package comeaa.workout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ErinA on 7/26/2017.
 */

public class ListContent {
    //public static ArrayList<ExerciseItem> myList = new ArrayList<>();


    private static Context context;

    public ListContent(Context c) {
        context = c;
    }

    //public void setList(ArrayList<ExerciseItem> exerciseList) {
    //    myList = exerciseList;
    //}
    public ArrayList<ExerciseItem> readDB(SQLiteDatabase db, Cursor cursor, ArrayList<ExerciseItem> exerciseList) {
        if (cursor.moveToFirst()) { // add all of the items from the DB to the list
            String exerciseName = cursor.getString(cursor.getColumnIndex(DbHelper.FeedEntry.COLUMN_NAME_EXERCISE));
            String sets = cursor.getString(cursor.getColumnIndex(DbHelper.FeedEntry.COLUMN_NAME_SETS));
            String reps = cursor.getString(cursor.getColumnIndex(DbHelper.FeedEntry.COLUMN_NAME_REPS));
            String  weight = cursor.getString(cursor.getColumnIndex(DbHelper.FeedEntry.COLUMN_NAME_WEIGHT));
            String max = cursor.getString(cursor.getColumnIndex(DbHelper.FeedEntry.COLUMN_NAME_MAX));
            ExerciseItem newExerciseItem = new ExerciseItem(exerciseName, sets, reps, weight, max);
            if (exerciseList.contains(newExerciseItem)) {  //if the exercise is in the db already
                exerciseList.remove(newExerciseItem);      //make sure it is an update version
                exerciseList.add(newExerciseItem);
            } else {
                exerciseList.add(newExerciseItem);
            }  ////else just add it if it's new
            while (cursor.moveToNext()) {
                exerciseName = cursor.getString(cursor.getColumnIndex(DbHelper.FeedEntry.COLUMN_NAME_EXERCISE));
                reps = cursor.getString(cursor.getColumnIndex(DbHelper.FeedEntry.COLUMN_NAME_REPS));
                weight = cursor.getString(cursor.getColumnIndex(DbHelper.FeedEntry.COLUMN_NAME_WEIGHT));
                max = cursor.getString(cursor.getColumnIndex(DbHelper.FeedEntry.COLUMN_NAME_MAX));
                newExerciseItem = new ExerciseItem(exerciseName,sets, reps, weight, max);
                if (exerciseList.contains(newExerciseItem)) { //if the exercise is in the db already
                    exerciseList.remove(newExerciseItem);     //make sure it is an update version
                    exerciseList.add(newExerciseItem);
                } else {
                    exerciseList.add(newExerciseItem);  //else just add it if it's new
                }
            }
        }
        return exerciseList;
    }


    public void updateDB(SQLiteDatabase db, DbHelper myDbHelper, ArrayList<ExerciseItem> exerciseList, String tableName) {
        myDbHelper.onDelete(db); //delete old db
        myDbHelper.onCreate(db); //create new db (this probs isn't efficient but was the best option for my last
                                    //interaction with a DB so I should change
        for (int i = 0; i < exerciseList.size(); i++) {
            ContentValues exerciseValues = new ContentValues();
            exerciseValues.put(DbHelper.FeedEntry.COLUMN_NAME_EXERCISE, exerciseList.get(i).getName());
            exerciseValues.put(DbHelper.FeedEntry.COLUMN_NAME_SETS, exerciseList.get(i).getSets());
            exerciseValues.put(DbHelper.FeedEntry.COLUMN_NAME_REPS, exerciseList.get(i).getReps());
            exerciseValues.put(DbHelper.FeedEntry.COLUMN_NAME_WEIGHT, exerciseList.get(i).getWeight());
            exerciseValues.put(DbHelper.FeedEntry.COLUMN_NAME_MAX, exerciseList.get(i).getMax());
            db.insert(tableName, null, exerciseValues); //insert new row
        }
    }


    //puts things into the database from the checklistModelItems array
    public void insertThings(SQLiteDatabase db, ArrayList<ExerciseItem> exerciseList) {
        for (int i = 0; i < exerciseList.size(); i++) {
            ContentValues exerciseValues = new ContentValues();
            exerciseValues.put(DbHelper.FeedEntry.COLUMN_NAME_EXERCISE, exerciseList.get(i).getName());
            exerciseValues.put(DbHelper.FeedEntry.COLUMN_NAME_SETS, exerciseList.get(i).getSets());
            exerciseValues.put(DbHelper.FeedEntry.COLUMN_NAME_REPS, exerciseList.get(i).getReps());
            exerciseValues.put(DbHelper.FeedEntry.COLUMN_NAME_WEIGHT, exerciseList.get(i).getWeight());
            exerciseValues.put(DbHelper.FeedEntry.COLUMN_NAME_MAX, exerciseList.get(i).getMax());
            db.insert(DbHelper.FeedEntry.TABLE_NAME, null, exerciseValues); //insert new row
        }

    }

    public void addListItem(ArrayList<ExerciseItem> exerciseItemArrayList, String name,String sets, String reps, String weight, String max) {
        ExerciseItem myItem = new ExerciseItem(name, sets, reps, weight, max);
        if (!exerciseItemArrayList.contains(myItem)) {
            exerciseItemArrayList.add(myItem);
        }
    }


    //fills checklistModelItems with the list of things to do
    public ArrayList<ExerciseItem> populateArray(ArrayList<ExerciseItem> exerciseList) {
        ExerciseItem thing1 = new ExerciseItem("Bench Press", "0", "0", "0", "0");
        ExerciseItem thing2 = new ExerciseItem("Chest Press", "0", "0", "0", "0");
        exerciseList.add(thing1);
        exerciseList.add(thing2);

        return exerciseList;

    }


}


