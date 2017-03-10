package jonathanoliveira.org.popularmovies.core.comm_interfaces;

import android.content.Context;

import jonathanoliveira.org.popularmovies.core.beans.Movie;

/**
 * Created by JonathanOliveira on 07/03/17.
 */

public interface CoreToAdapter_Interface {

    void updateAdapter(Movie[] movieResult);

    void writeError();

    Context getContext();

}