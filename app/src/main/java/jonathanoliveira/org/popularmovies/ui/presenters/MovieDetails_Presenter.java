package jonathanoliveira.org.popularmovies.ui.presenters;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import jonathanoliveira.org.popularmovies.core.Core;
import jonathanoliveira.org.popularmovies.core.Core_Interface;
import jonathanoliveira.org.popularmovies.core.beans.Movie;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToPresenter_Interface;
import jonathanoliveira.org.popularmovies.ui.views.MovieDetails_Activity_Interface;

/**
 * Created by JonathanOliveira on 06/03/17.
 */

public class MovieDetails_Presenter implements MovieDetails_Presenter_Interface, CoreToPresenter_Interface {

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

    @Override
    public void bindViewWithPicasso(String moviePosterPath) {
        Core_Interface core = Core.getCoreInstance();
        core.registerCoreToPresenterInterface(this);
        core.bindData(moviePosterPath);
    }

    @Override
    public Context getContext() {
        return activity.getContext();
    }

    @Override
    public ImageView getImageView() {
        return activity.getImageView();
    }
}
