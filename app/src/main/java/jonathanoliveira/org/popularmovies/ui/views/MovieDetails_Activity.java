package jonathanoliveira.org.popularmovies.ui.views;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

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
    private TextView movieTrailerNumberTextView;
    private TextView movieReviewNumberTextView;
    private ToggleButton movieFavButton;

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
        movieTrailerNumberTextView = (TextView) findViewById(R.id.new_trailer_number);
        movieReviewNumberTextView = (TextView) findViewById(R.id.new_review_number);
        movieFavButton = (ToggleButton) findViewById(R.id.new_movie_fav_btn);
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
//        String BASIC_TRAILER_TEXT = getString(R.string.basic_trailer_text);
//        String RELEASE_DATE_LABEL_TEXT = "Release date: ";
//        String SYNOPSIS_LABEL_TEXT = "Plot Synopsis: \n";
        String movieName = movie.getMovie_title();
        String moviePosterPath = movie.getPoster_path();
//        String movieOverview = SYNOPSIS_LABEL_TEXT + movie.getOverview();
        String movieOverview = movie.getOverview();
//        String movieRating = RATING_LABEL_TEXT + movie.getVote_average();
        String movieRating = "Rate: " + movie.getVote_average() + RATING_LABEL_TEXT;
//        String movieReleaseDate = RELEASE_DATE_LABEL_TEXT + movie.getRelease_date();
        String movieReleaseDate = movie.getRelease_date();
        presenter.bindViewWithPicasso(moviePosterPath);
        movieNameTextView.setText(movieName);
        movieOverviewTextView.setText(movieOverview);
        movieRatingTextView.setText(movieRating);
        movieRDTextView.setText(movieReleaseDate);
        movieTrailerNumberTextView.setText("0 " + getString(R.string.basic_trailer_text) + "s");
        movieReviewNumberTextView.setText("0 " + getString(R.string.basic_review_text) + "s");
    }

    @Override
    public void bindTrailersToViews() {
        Movie movie = presenter.getObjectFromPresenter();
        String[][] allTrailers = movie.getTrailers();
        int numTrailers = allTrailers.length;
        movieTrailerNumberTextView.setText(numTrailers + " " + getString(R.string.basic_trailer_text) + "s");
        int[] viewIds = new int[numTrailers];
        if (numTrailers >= 1) {
            findViewById(R.id.trailer_1_frame).setVisibility(View.VISIBLE);
            viewIds[0] = R.id.new_trailer_name_1;
        }
        if (numTrailers >= 2) {
            findViewById(R.id.trailer_2_frame).setVisibility(View.VISIBLE);
            viewIds[1] = R.id.new_trailer_name_2;
        }
        if (numTrailers >= 3) {
            findViewById(R.id.trailer_3_frame).setVisibility(View.VISIBLE);
            viewIds[2] = R.id.new_trailer_name_3;
        }

        int display_width = presenter.getDisplayWidth();
        Double trailerNameWidth = display_width * 0.6;

        for (int i=0; i < numTrailers; i++) {
            TextView trailerName = (TextView) findViewById(viewIds[i]);
            trailerName.setWidth(trailerNameWidth.intValue());
            trailerName.setSingleLine(true);
            trailerName.setText(allTrailers[i][0]);
            trailerName.setEllipsize(TextUtils.TruncateAt.END);
        }
    }

    @Override
    public void bindReviewsToViews() {
        Movie movie = presenter.getObjectFromPresenter();
        String[][] allReviews = movie.getReviews();
        int numReviews = allReviews.length;
        movieReviewNumberTextView.setText(numReviews + " " + getString(R.string.basic_review_text) + "s");
        int[] authorIds = new int[numReviews];
        int[] reviewIds = new int[numReviews];
        if (numReviews >= 1) {
            findViewById(R.id.review_1_frame).setVisibility(View.VISIBLE);
            authorIds[0] = R.id.new_review_author_1;
            reviewIds[0] = R.id.new_review_text_1;
        }
        if (numReviews >= 2) {
            findViewById(R.id.review_2_frame).setVisibility(View.VISIBLE);
            authorIds[1] = R.id.new_review_author_2;
            reviewIds[1] = R.id.new_review_text_2;
        }
        if (numReviews >= 3) {
            findViewById(R.id.review_3_frame).setVisibility(View.VISIBLE);
            authorIds[2] = R.id.new_review_author_3;
            reviewIds[2] = R.id.new_review_text_3;
        }

        for (int i=0; i < numReviews; i++) {
            TextView authorName = (TextView) findViewById(authorIds[i]);
            authorName.setSingleLine(true);
            authorName.setText(getString(R.string.new_review_author_name) + " " + allReviews[i][0]);
            authorName.setEllipsize(TextUtils.TruncateAt.END);

            TextView reviewText = (TextView) findViewById(reviewIds[i]);
            reviewText.setText(allReviews[i][1]);
        }
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
//            NavUtils.navigateUpFromSameTask(this);
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public LoaderManager getActivityLoaderManager() {
        return getSupportLoaderManager();
    }

    @Override
    public WindowManager getActivityWindowManager() {
        return getWindowManager();
    }

    @Override
    public PackageManager getActivityPackageManager() {
        return getPackageManager();
    }

    @Override
    public void startNewActivityWithIntent(Intent intent) {
        startActivity(intent);
    }

    public void onClickTrailerButton(View view) {
        presenter.handleTrailerClick(view);
    }

    public void favoriteClickListener(View view) {
        presenter.favoriteClickListener();
    }

    @Override
    public ContentResolver getActivityContentResolver() {
        return getContentResolver();
    }

    @Override
    public void setFavoriteToggleButton() {
        boolean isFavorite = presenter.getObjectFromPresenter().isFavorite();
        movieFavButton.setChecked(isFavorite);
    }
}
