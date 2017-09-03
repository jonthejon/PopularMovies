package jonathanoliveira.org.popularmovies.data.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by JonathanOliveira on 17/03/17.
 */

public class FavoritesContentProvider extends ContentProvider {

    private FavoriteMoviesDbHelper dbHelper;
    public static final String TABLE_TO_ACT = "table";


    @Override
    public boolean onCreate() {
        Context context = getContext();
        this.dbHelper = new FavoriteMoviesDbHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        final SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(FavoriteMoviesContract.FavoriteMoviesEntry.TABLE_NAME);
        if (CPHandler.buildUriMatcher().match(uri) == CPHandler.SINGLE_MOVIE_WITH_ID) {
            queryBuilder.appendWhere("_ID=" + ContentUris.parseId(uri));
        }

        Cursor c = queryBuilder.query(db,
                null,
                null,
                null,
                null,
                null,
                null);
        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
//        String table = values.getAsString(TABLE_TO_ACT);
        Log.d("JONATHAN", "Inserted in DB: ");
//        values.remove(TABLE_TO_ACT);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
//        Uri returnUri;
        long id = db.insert(FavoriteMoviesContract.FavoriteMoviesEntry.TABLE_NAME, null, values);
//        if (id > 0) {
//            returnUri = ContentUris.withAppendedId(MOVIES_URI, id);
//        } else {
//            throw new android.database.SQLException("Failed to insert row into " + uri);
//        }
//        return returnUri;
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
//        get your write only final SQLiteDatabase db using the dbHelper.getWritableDatabase() method
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (CPHandler.buildUriMatcher().match(uri)) {
            case CPHandler.DELETE_SINGLE_MOVIE_WITH_ID:
                Log.d("JONATHAN", "Deleted from DB: ");
                db.delete(FavoriteMoviesContract.FavoriteMoviesEntry.TABLE_NAME,selection,null);
                break;
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

}
