package jonathanoliveira.org.popularmovies.ui.presenters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import jonathanoliveira.org.popularmovies.R;
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
    private String poster_path;

    public MovieDetails_Presenter(MovieDetails_Activity_Interface activity) {
        this.activity = activity;
    }

    @Override
    public void operateOnCallerIntent(Intent callerIntent) {
        if (callerIntent.hasExtra(Intent.EXTRA_TEMPLATE)) {
            movie = callerIntent.getParcelableExtra(Intent.EXTRA_TEMPLATE);
            activity.bindDataToViews();
            Core_Interface core = Core.getCoreInstance();
            core.registerCoreToPresenterInterface(this);
            core.getData(core.getPresenterTrailersChannel());
        }
    }

    @Override
    public Movie getObjectFromPresenter() {
        return movie;
    }

    @Override
    public void bindViewWithPicasso(String moviePosterPath) {
        this.poster_path = moviePosterPath;
        Core_Interface core = Core.getCoreInstance();
        core.registerCoreToPresenterInterface(this);
//        core.bindData(moviePosterPath);
        core.bindData(core.getPresenterChannel());
    }

    @Override
    public void bindTrailerstoViews(String[][] trailers) {
//        Log.d("JONATHAN", "bindTrailerstoViews: " + trailers.length);
        movie.setTrailers(trailers);
        activity.bindTrailersToViews();
    }

    @Override
    public void handleTrailerClick(View view) {
        int id = view.getId();
        String stringURL = null;
        switch (id) {
            case R.id.new_play_btn_1:
                stringURL = movie.getTrailers()[0][1];
                break;
            case R.id.new_play_btn_2:
                stringURL = movie.getTrailers()[1][1];
                break;
            case R.id.new_play_btn_3:
                stringURL = movie.getTrailers()[2][1];
                break;
        }

        Uri trailerLink = Uri.parse(stringURL);
        Intent intent = new Intent(Intent.ACTION_VIEW, trailerLink);
        if (intent.resolveActivity(activity.getActivityPackageManager()) != null) {
            activity.startNewActivityWithIntent(intent);
        }
    }

    @Override
    public Context getContext() {
        return activity.getContext();
    }

    @Override
    public ImageView getImageView() {
        return activity.getImageView();
    }

    @Override
    public String getString() {
        return this.poster_path;
    }

    @Override
    public LoaderManager getLoaderManager() {
        return activity.getActivityLoaderManager();
    }

    @Override
    public int getMovieId() {
        return movie.getMovie_id();
    }

    @Override
    public int getDisplayWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getActivityWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
}
