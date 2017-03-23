package jonathanoliveira.org.popularmovies.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import jonathanoliveira.org.popularmovies.data.database.FavoriteMoviesContract.FavoriteMoviesEntry;

/**
 * Created by JonathanOliveira on 17/03/17.
 */

public class FavoriteMoviesDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favoritemovies.db";

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 5;

    public FavoriteMoviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_MOVIES_TABLE = "CREATE TABLE " + FavoriteMoviesEntry.TABLE_NAME + " (" +
                FavoriteMoviesEntry.COLUMN_MOVIE_ID + " INTEGER PRIMARY KEY, " +
                FavoriteMoviesEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                FavoriteMoviesEntry.COLUMN_POSTER_PATH + " TEXT, " +
                FavoriteMoviesEntry.COLUMN_OVERVIEW + " TEXT, " +
                FavoriteMoviesEntry.COLUMN_RELEASE_DATE + " TEXT, " +
                FavoriteMoviesEntry.COLUMN_VOTE_AVERAGE + " TEXT" +
                "); ";

/*        final String SQL_CREATE_RESOURCES_TABLE = "CREATE TABLE " + ResourcesMoviesEntry.TABLE_NAME_REC + " (" +
                ResourcesMoviesEntry.COLUMN_MOVIE_ID_REC + " INTEGER NOT NULL, " +
                ResourcesMoviesEntry.COLUMN_TYPE_REC + " TEXT NOT NULL, " +
                ResourcesMoviesEntry.COLUMN_OWNER_REC + " TEXT NOT NULL, " +
                ResourcesMoviesEntry.COLUMN_RESOURCE_REC + " TEXT NOT NULL" +
                "); ";*/

        db.execSQL(SQL_CREATE_MOVIES_TABLE);
//        db.execSQL(SQL_CREATE_RESOURCES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // For now simply drop the table and create a new one. This means if you change the
        // DATABASE_VERSION the table will be dropped.
        // In a production app, this method might be modified to ALTER the table
        // instead of dropping it, so that existing data is not deleted.
        db.execSQL("DROP TABLE IF EXISTS " + FavoriteMoviesEntry.TABLE_NAME);
//        db.execSQL("DROP TABLE IF EXISTS " + ResourcesMoviesEntry.TABLE_NAME_REC);
        onCreate(db);
    }
}
