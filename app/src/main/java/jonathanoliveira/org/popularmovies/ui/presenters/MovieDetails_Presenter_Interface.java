package jonathanoliveira.org.popularmovies.ui.presenters;

import android.content.Intent;
import android.view.View;

import jonathanoliveira.org.popularmovies.core.beans.Movie;

/**
 * Created by JonathanOliveira on 06/03/17.
 */

public interface MovieDetails_Presenter_Interface {

    void operateOnCallerIntent(Intent callerIntent);

    Movie getObjectFromPresenter();

    void bindViewWithPicasso(String moviePosterPath);

    int getDisplayWidth();

    void handleTrailerClick(View view);

}
