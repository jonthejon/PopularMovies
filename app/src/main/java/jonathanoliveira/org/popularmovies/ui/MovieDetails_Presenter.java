package jonathanoliveira.org.popularmovies.ui;

import android.content.Intent;

import jonathanoliveira.org.popularmovies.Movie;
import jonathanoliveira.org.popularmovies.comm_interfaces.Base_Views;
import jonathanoliveira.org.popularmovies.comm_interfaces.CallerIntent_Presenter;

/**
 * Created by JonathanOliveira on 06/03/17.
 */

public class MovieDetails_Presenter implements CallerIntent_Presenter {

    private Base_Views activity;
    private Movie movie;

    public MovieDetails_Presenter(Base_Views activity) {
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
