package jonathanoliveira.org.popularmovies.ui;

import android.content.Intent;

import jonathanoliveira.org.popularmovies.Movie;
import jonathanoliveira.org.popularmovies.comm_interfaces.MovieDetails_Activity_Interface;
import jonathanoliveira.org.popularmovies.comm_interfaces.MovieDetails_Presenter_Interface;

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
        if (callerIntent.hasExtra(Intent.EXTRA_TEXT)) {
            movie = (Movie) callerIntent.getSerializableExtra(Intent.EXTRA_TEXT);
            activity.bindDataToViews();
        }
    }

    @Override
    public Movie getObjectFromPresenter() {
        return movie;
    }

}
