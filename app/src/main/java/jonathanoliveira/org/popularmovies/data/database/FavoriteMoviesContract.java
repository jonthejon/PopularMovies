package jonathanoliveira.org.popularmovies.data.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by JonathanOliveira on 17/03/17.
 */

public class FavoriteMoviesContract {

    // The authority, which is how your code knows which Content Provider to access
    public static final String AUTHORITY = "jonathanoliveira.org.popularmovies";

    // The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    // Define the possible paths for accessing data in this contract
    // This is the path for the "tasks" directory
    public static final String PATH_MOVIES = "movies";
    public static final String PATH_MOVIE = "movie";
    public static final String PATH_RESOURCES = "resources";

/*    public static final String TRAILER_TYPE = "trailer";
    public static final String REVIEW_TYPE = "review";*/


    public static final class FavoriteMoviesEntry implements BaseColumns {

        public static final Uri MOVIES_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();
        public static final Uri MOVIE_INSERT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES)
                        .appendPath("insert")
                        .build();
        public static final Uri MOVIE_DELETE_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES)
                        .appendPath("delete")
                        .build();

        public static final String TABLE_NAME = "movies";
        public static final String COLUMN_MOVIE_ID = "_ID";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
    }
/*
    public static final class ResourcesMoviesEntry implements BaseColumns {

        public static final Uri RESOURCES_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_RESOURCES).build();

        public static final String TABLE_NAME_REC = "resources";
        public static final String COLUMN_MOVIE_ID_REC = "resource_id";
        public static final String COLUMN_TYPE_REC = "type";
        public static final String COLUMN_OWNER_REC = "owner";
        public static final String COLUMN_RESOURCE_REC = "resource";
    }*/

}
