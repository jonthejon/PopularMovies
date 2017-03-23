package jonathanoliveira.org.popularmovies.ui.helpers;

import android.content.Context;

import jonathanoliveira.org.popularmovies.core.beans.Movie;

/**
 * Created by JonathanOliveira on 07/03/17.
 */

public interface GridAdapter_Interface {

    Movie getAdapterDataSingleItem(int position);

    void notifyAdapterForDataChange(Movie[] moviesArr);

    void onAdapterClickCallback(int position);

    Context getContext();

    Movie[] getMoviesArr();

}
