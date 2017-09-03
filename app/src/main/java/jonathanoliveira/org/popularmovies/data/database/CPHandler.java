package jonathanoliveira.org.popularmovies.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;

import java.util.ArrayList;

import jonathanoliveira.org.popularmovies.core.Core;
import jonathanoliveira.org.popularmovies.core.beans.Movie;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToPresenter_Interface;

import static jonathanoliveira.org.popularmovies.core.Core.getCoreInstance;
import static jonathanoliveira.org.popularmovies.data.database.FavoriteMoviesContract.AUTHORITY;
import static jonathanoliveira.org.popularmovies.data.database.FavoriteMoviesContract.PATH_MOVIES;
import static jonathanoliveira.org.popularmovies.data.database.FavoriteMoviesContract.PATH_RESOURCES;

/**
 * Created by JonathanOliveira on 20/03/17.
 */

public class CPHandler implements LoaderManager.LoaderCallbacks<Cursor>, ContentProviderHandler_Interface {

    // It's convention to use 100, 200, 300, etc for directories,
    // and related ints (101, 102, ..) for items in that directory.
    public static final int ALL_MOVIES = 100;
    public static final int SINGLE_MOVIE_WITH_ID = 101;
    public static final int INSERT_SINGLE_MOVIE_WITH_ID = 102;
    public static final int DELETE_SINGLE_MOVIE_WITH_ID = 103;
    public static final int TRAILERS_WITH_ID = 201;
    public static final int REVIEWS_WITH_ID = 202;

    public static final int CHECK_CURSOR_LOADER_ID = 9;
    public static final int ALL_CURSOR_LOADER_ID = 13;

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case CHECK_CURSOR_LOADER_ID:
                CoreToPresenter_Interface presenter = getCoreInstance().getPresenterInstance();
                int movie_id = presenter.getMovieId();
                Uri content_uri = presenter.getPresenterUri();
                Context context = presenter.getContext();
//        Log.d(TAG, "onCreateLoader: " + content_uri.toString());
//                CursorLoader cursorLoader = new CursorLoader()
                return new CursorLoader(context, content_uri, null, null, null, null);

            case ALL_CURSOR_LOADER_ID:
                Log.d("JONATHAN", "getDataFromDataManager: STARTING PROPER LOADER");
                Uri allMoviesUri = FavoriteMoviesContract.FavoriteMoviesEntry.MOVIES_URI;
                Log.d("JONATHAN", "getDataFromDataManager: " + allMoviesUri);
                Context adapter_context = getCoreInstance().getAdapterPresenterInstance().getContext();
                return new CursorLoader(adapter_context, allMoviesUri, null, null, null, null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        Log.d("JONATHAN", "onCreateLoader: size of Cursor is " + data.getCount());
        int loader_id = loader.getId();
        switch (loader_id) {
            case CHECK_CURSOR_LOADER_ID:
                boolean isFav = data.getCount() > 0;
                getCoreInstance().getPresenterInstance().checkFavoriteCallback(isFav);
                break;
            case ALL_CURSOR_LOADER_ID:
                if (data == null || data.getCount() == 0) {
                    getCoreInstance().returnData(null);
                    break;
                }

                ArrayList<Movie> movieArrayList = new ArrayList<>();
                int id_index = data.getColumnIndex(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_MOVIE_ID);
                int title_index = data.getColumnIndex(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_TITLE);
                int poster_index = data.getColumnIndex(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_POSTER_PATH);
                int overview_index = data.getColumnIndex(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_OVERVIEW);
                int rd_index = data.getColumnIndex(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_RELEASE_DATE);
                int vote_index = data.getColumnIndex(FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_VOTE_AVERAGE);
//                data.moveToFirst();
                while (data.moveToNext()) {
                    Movie movie = new Movie.MovieBuilder(data.getInt(id_index))
                            .movie_title(data.getString(title_index))
                            .poster_path(data.getString(poster_index))
                            .overview(data.getString(overview_index))
                            .release_date(data.getString(rd_index))
                            .vote_average(Double.valueOf(data.getString(vote_index)))
                            .build();
                    movie.setFavorite(true);
                    movieArrayList.add(movie);
                }
                Movie[] movieArr = new Movie[movieArrayList.size()];
                movieArr = movieArrayList.toArray(movieArr);
                getCoreInstance().returnData(movieArr);
//                Manager.getManagerInstance().dataCPCallback(data);
//                Core.getCoreInstance().getPresenterInstance().checkFavoriteCallback(isFav);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    //    private static final UriMatcher sUriMatcher = buildUriMatcher();

/*    public FavoritesContentProvider() {
        Context context = getContext();
        this.dbHelper = new FavoriteMoviesDbHelper(context);
    }*/

    /**
     * Initialize a new matcher object without any matches,
     * then use .addURI(String authority, String path, int match) to add matches
     */
    public static UriMatcher buildUriMatcher() {

        // Initialize a UriMatcher with no matches by passing in NO_MATCH to the constructor
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        /*
          All paths added to the UriMatcher have a corresponding int.
          For each kind of uri you may want to access, add the corresponding match with addURI.
          The two calls below add matches for the task directory and a single item by ID.
         */
        uriMatcher.addURI(AUTHORITY, PATH_MOVIES, ALL_MOVIES);
//        MAINTAIN THIS LINE OF CODE TO REMEMBER HOW TO USE #
//        uriMatcher.addURI(AUTHORITY, PATH_MOVIES + "/get/movie/#", SINGLE_MOVIE_WITH_ID);
        uriMatcher.addURI(AUTHORITY, PATH_MOVIES + "/#", SINGLE_MOVIE_WITH_ID);
        uriMatcher.addURI(AUTHORITY, PATH_MOVIES + "/insert", INSERT_SINGLE_MOVIE_WITH_ID);
        uriMatcher.addURI(AUTHORITY, PATH_MOVIES + "/delete", DELETE_SINGLE_MOVIE_WITH_ID);
        uriMatcher.addURI(AUTHORITY, PATH_RESOURCES + "/get/trailers/#", TRAILERS_WITH_ID);
        uriMatcher.addURI(AUTHORITY, PATH_RESOURCES + "/get/reviews/#", REVIEWS_WITH_ID);

        return uriMatcher;
    }


    @Override
    public void operateOnSingleMovie() {
        CoreToPresenter_Interface presenter = getCoreInstance().getPresenterInstance();
        Uri uri = presenter.getPresenterUri();
        new CPAsyncTask().execute(uri);
    }

    @Override
    public void getSingleMovie() {

    }

    @Override
    public void checkMovieFavorite() {
//        getCoreInstance().getPresenterInstance().getLoaderManager().initLoader(CHECK_CURSOR_LOADER_ID,null,this);
        LoaderManager loaderManager = Core.getCoreInstance().getPresenterInstance().getLoaderManager();
        Loader<String> loader = loaderManager.getLoader(CHECK_CURSOR_LOADER_ID);
        if (loader == null) {
            loaderManager.initLoader(CHECK_CURSOR_LOADER_ID, null, this).forceLoad();
        } else {
            loaderManager.restartLoader(CHECK_CURSOR_LOADER_ID, null, this).forceLoad();
        }
    }

    @Override
    public void getFavoriteMovies() {
        LoaderManager loaderManager = Core.getCoreInstance().getAdapterPresenterInstance().getLoaderManager();
        Loader<String> loader = loaderManager.getLoader(ALL_CURSOR_LOADER_ID);
        if (loader == null) {
            loaderManager.initLoader(ALL_CURSOR_LOADER_ID, null, this).forceLoad();
        } else {
            loaderManager.restartLoader(ALL_CURSOR_LOADER_ID, null, this).forceLoad();
        }
    }

    @Override
    public void getTrailers() {

    }

    @Override
    public void getReviews() {

    }

    @Override
    public void deleteMovie() {

    }

    @Override
    public Uri getUri() {
        return null;
    }

    @Override
    public ContentValues getMovieContentValues() {
        return null;
    }

    @Override
    public ContentValues getTrailerContentValues() {
        return null;
    }

    @Override
    public ContentValues getReviewContentValues() {
        return null;
    }
}
