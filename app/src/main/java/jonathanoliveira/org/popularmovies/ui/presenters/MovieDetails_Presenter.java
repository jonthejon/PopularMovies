package jonathanoliveira.org.popularmovies.ui.presenters;

import android.content.Intent;

import jonathanoliveira.org.popularmovies.core.beans.Movie;
import jonathanoliveira.org.popularmovies.ui.views.MovieDetails_Activity_Interface;

/**
 * Created by JonathanOliveira on 06/03/17.
 */

public class MovieDetails_Presenter implements MovieDetails_Presenter_Interface {

    private MovieDetails_Activity_Interface activity;
    private Movie movie;

    public MovieDetails_Presenter(MovieDetails_Activity_Interface activity) {
        this.activity = activity;
    }

    @Override
    public void operateOnCallerIntent(Intent callerIntent) {
        if (callerIntent.hasExtra(Intent.EXTRA_TEMPLATE)) {
            movie = callerIntent.getParcelableExtra(Intent.EXTRA_TEMPLATE);
            activity.bindDataToViews();
        }
    }

    @Override
    public Movie getObjectFromPresenter() {
        return movie;
    }

}
