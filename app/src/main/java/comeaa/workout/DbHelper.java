package comeaa.workout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by ErinA on 7/25/2017.
 */

    public class DbHelper extends SQLiteOpenHelper {
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "workouts";
        public static final String COLUMN_NAME_EXERCISE = "exercise";
        public static final String COLUMN_NAME_SETS = "sets";
        public static final String COLUMN_NAME_WEIGHT = "weight";
        public static final String COLUMN_NAME_REPS = "reps";
        public static final String COLUMN_NAME_MAX = "max";
        //public static final String COLUMN_NAME_CHECKED = "checked";
    }


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "WorkoutList";

    //public static final String IS_CHECKED = "is_checked";


    private static final String SQL_CREATE_ENTRIES = " CREATE TABLE " +
            DbHelper.FeedEntry.TABLE_NAME + " (" + DbHelper.FeedEntry._ID + " INTEGER PRIMARY KEY,"
            + FeedEntry.COLUMN_NAME_EXERCISE + " TEXT," + FeedEntry.COLUMN_NAME_SETS + " TEXT,"
            + FeedEntry.COLUMN_NAME_REPS + " TEXT," +
            FeedEntry.COLUMN_NAME_WEIGHT + " TEXT," + FeedEntry.COLUMN_NAME_MAX + " Text)";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onDelete(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME);
    }

    public void onUpgrade(SQLiteDatabase db, int OldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME);
        onCreate(db);
    }


}


