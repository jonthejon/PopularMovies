package jonathanoliveira.org.popularmovies.data.database;

import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;

import jonathanoliveira.org.popularmovies.core.Core;
import jonathanoliveira.org.popularmovies.core.beans.Movie;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToPresenter_Interface;

import static jonathanoliveira.org.popularmovies.data.database.CPHandler.DELETE_SINGLE_MOVIE_WITH_ID;
import static jonathanoliveira.org.popularmovies.data.database.CPHandler.INSERT_SINGLE_MOVIE_WITH_ID;
import static jonathanoliveira.org.popularmovies.data.database.CPHandler.buildUriMatcher;
import static jonathanoliveira.org.popularmovies.data.database.FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_MOVIE_ID;
import static jonathanoliveira.org.popularmovies.data.database.FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_OVERVIEW;
import static jonathanoliveira.org.popularmovies.data.database.FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_POSTER_PATH;
import static jonathanoliveira.org.popularmovies.data.database.FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_RELEASE_DATE;
import static jonathanoliveira.org.popularmovies.data.database.FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_TITLE;
import static jonathanoliveira.org.popularmovies.data.database.FavoriteMoviesContract.FavoriteMoviesEntry.COLUMN_VOTE_AVERAGE;

/**
 * Created by JonathanOliveira on 20/03/17.
 */

public class CPAsyncTask extends AsyncTask<Uri, Void, Void> {

    @Override
    protected Void doInBackground(Uri... params) {

        if (params.length == 0 || params[0] == null) {
            return null;
        }

        CoreToPresenter_Interface presenter = Core.getCoreInstance().getPresenterInstance();
        Movie movie = presenter.getMovie();
        int movie_id = movie.getMovie_id();
//        ContentValues resourcesValues;
        int match = buildUriMatcher().match(params[0]);
        switch (match) {

            case INSERT_SINGLE_MOVIE_WITH_ID:

                ContentValues movieValues = new ContentValues();
//                movieValues.put(TABLE_TO_ACT, TABLE_NAME);
                movieValues.put(COLUMN_MOVIE_ID, movie_id);
                movieValues.put(COLUMN_TITLE, movie.getMovie_title());
                movieValues.put(COLUMN_POSTER_PATH, movie.getPoster_path());
                movieValues.put(COLUMN_OVERVIEW, movie.getOverview());
                movieValues.put(COLUMN_RELEASE_DATE, movie.getRelease_date());
                movieValues.put(COLUMN_VOTE_AVERAGE, Double.valueOf(movie.getVote_average()).toString());
                presenter.getActivityContentResolver().insert(params[0],movieValues);
                movie.setFavorite(true);

/*                if (movie.getTrailers().length > 0) {
                    for (String[] trailer : movie.getTrailers()) {
                        resourcesValues = new ContentValues();
                        resourcesValues.put(TABLE_TO_ACT, TABLE_NAME_REC);
                        resourcesValues.put(COLUMN_MOVIE_ID_REC, movie_id);
                        resourcesValues.put(COLUMN_TYPE_REC, TRAILER_TYPE);
                        resourcesValues.put(COLUMN_OWNER_REC, trailer[0]);
                        resourcesValues.put(COLUMN_RESOURCE_REC, trailer[1]);
                        presenter.getActivityContentResolver().insert(params[0],resourcesValues);
                    }
                }
                if (movie.getReviews().length > 0) {
                    for (String[] review : movie.getReviews()) {
                        resourcesValues = new ContentValues();
                        resourcesValues.put(TABLE_TO_ACT, TABLE_NAME_REC);
                        resourcesValues.put(COLUMN_MOVIE_ID_REC, movie_id);
                        resourcesValues.put(COLUMN_TYPE_REC, REVIEW_TYPE);
                        resourcesValues.put(COLUMN_OWNER_REC, review[0]);
                        resourcesValues.put(COLUMN_RESOURCE_REC, review[1]);
                        presenter.getActivityContentResolver().insert(params[0],resourcesValues);
                    }
                }*/
                break;

            case DELETE_SINGLE_MOVIE_WITH_ID:

                Uri base_uri = params[0];
                String where_clause = "_ID=" + movie_id;
                presenter.getActivityContentResolver().delete(base_uri,where_clause,null);
                movie.setFavorite(false);
                break;
        }

        return null;
    }
}
