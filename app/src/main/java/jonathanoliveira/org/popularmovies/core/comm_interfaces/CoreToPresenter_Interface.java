package jonathanoliveira.org.popularmovies.core.comm_interfaces;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.widget.ImageView;

import jonathanoliveira.org.popularmovies.core.beans.Movie;

/**
 * Created by JonathanOliveira on 10/03/17.
 */

public interface CoreToPresenter_Interface {

    Context getContext();

    ImageView getImageView();

    String getString();

    LoaderManager getLoaderManager();

    int getMovieId();

    void bindTrailerstoViews(String[][] trailers);

    void bindReviewstoViews(String[][] reviews);

    Movie getMovie();

    Uri getPresenterUri();
    void setPresenterUri(Uri presenterUri);

    ContentResolver getActivityContentResolver();

    void checkFavoriteCallback(boolean isFavorite);

}
