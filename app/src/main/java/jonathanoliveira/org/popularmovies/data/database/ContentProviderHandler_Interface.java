package jonathanoliveira.org.popularmovies.data.database;

import android.content.ContentValues;
import android.net.Uri;

/**
 * Created by JonathanOliveira on 17/03/17.
 */

public interface ContentProviderHandler_Interface {

    void operateOnSingleMovie();

    void getSingleMovie();

    void checkMovieFavorite();

    void getFavoriteMovies();

    void getTrailers();

    void getReviews();

    void deleteMovie();

    Uri getUri();

    ContentValues getMovieContentValues();
    ContentValues getTrailerContentValues();
    ContentValues getReviewContentValues();

}
