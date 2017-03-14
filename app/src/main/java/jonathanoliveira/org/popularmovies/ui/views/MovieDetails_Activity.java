package jonathanoliveira.org.popularmovies.ui.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import jonathanoliveira.org.popularmovies.R;
import jonathanoliveira.org.popularmovies.core.beans.Movie;
import jonathanoliveira.org.popularmovies.ui.presenters.MovieDetails_Presenter;
import jonathanoliveira.org.popularmovies.ui.presenters.MovieDetails_Presenter_Interface;

public class MovieDetails_Activity extends AppCompatActivity implements MovieDetails_Activity_Interface {

    private TextView movieNameTextView;
    private ImageView movieImageView;
    private TextView movieOverviewTextView;
    private TextView movieRatingTextView;
    private TextView movieRDTextView;
    private MovieDetails_Presenter_Interface presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_movie_details);
        setContentView(R.layout.yet_another_layout);
        setActionBar();
        wireViews();
        presenter = new MovieDetails_Presenter(this);
        presenter.operateOnCallerIntent(getIntent());
    }

    @Override
    public void setActionBar() {
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void wireViews() {
        movieNameTextView = (TextView) findViewById(R.id.new_movie_title);
        movieImageView = (ImageView) findViewById(R.id.new_movie_poster);
        movieRatingTextView = (TextView) findViewById(R.id.new_movie_rating);
        movieRDTextView = (TextView) findViewById(R.id.new_movie_year);
        movieOverviewTextView = (TextView) findViewById(R.id.new_movie_overview);
/*        movieNameTextView = (TextView) findViewById(R.id.movie_name_detail);
        movieImageView = (ImageView) findViewById(R.id.poster_detail);
        movieOverviewTextView = (TextView) findViewById(R.id.movie_overview_detail);
        movieRatingTextView = (TextView) findViewById(R.id.movie_rating_detail);
        movieRDTextView = (TextView) findViewById(R.id.movie_release_date_detail);*/
    }

    @Override
    public void bindDataToViews() {
        Movie movie = presenter.getObjectFromPresenter();
//        String RATING_LABEL_TEXT = "User rating: ";
        String RATING_LABEL_TEXT = getString(R.string.ratings_cont);
//        String RELEASE_DATE_LABEL_TEXT = "Release date: ";
//        String SYNOPSIS_LABEL_TEXT = "Plot Synopsis: \n";
        String movieName = movie.getMovie_title();
        String moviePosterPath = movie.getPoster_path();
//        String movieOverview = SYNOPSIS_LABEL_TEXT + movie.getOverview();
        String movieOverview = movie.getOverview();
//        String movieRating = RATING_LABEL_TEXT + movie.getVote_average();
        String movieRating = movie.getVote_average() + RATING_LABEL_TEXT;
//        String movieReleaseDate = RELEASE_DATE_LABEL_TEXT + movie.getRelease_date();
        String movieReleaseDate = movie.getRelease_date();
        presenter.bindViewWithPicasso(moviePosterPath);
        movieNameTextView.setText(movieName);
        movieOverviewTextView.setText(movieOverview);
        movieRatingTextView.setText(movieRating);
        movieRDTextView.setText(movieReleaseDate);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public ImageView getImageView() {
        return movieImageView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
