package jonathanoliveira.org.popularmovies.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jonathanoliveira.org.popularmovies.Movie;
import jonathanoliveira.org.popularmovies.NetworkUtils;
import jonathanoliveira.org.popularmovies.R;
import jonathanoliveira.org.popularmovies.comm_interfaces.Base_Views;
import jonathanoliveira.org.popularmovies.comm_interfaces.CallerIntent_Presenter;

public class MovieDetails_Activity extends AppCompatActivity implements Base_Views {

    private TextView movieNameTextView;
    private ImageView movieImageView;
    private TextView movieOverviewTextView;
    private TextView movieRatingTextView;
    private TextView movieRDTextView;
    private CallerIntent_Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        wireViews();
        presenter = new MovieDetails_Presenter(this);
        presenter.operateOnCallerIntent(getIntent());
    }

    @Override
    public void wireViews() {
        movieNameTextView = (TextView) findViewById(R.id.movie_name_detail);
        movieImageView = (ImageView) findViewById(R.id.poster_detail);
        movieOverviewTextView = (TextView) findViewById(R.id.movie_overview_detail);
        movieRatingTextView = (TextView) findViewById(R.id.movie_rating_detail);
        movieRDTextView = (TextView) findViewById(R.id.movie_release_date_detail);
    }

    @Override
    public void bindDataToViews() {
        Movie movie = presenter.getObjectFromPresenter();
        String RATING_LABEL_TEXT = "User rating: ";
        String RELEASE_DATE_LABEL_TEXT = "Release date: ";
        String SYNOPSIS_LABEL_TEXT = "Plot Synopsis: \n";
        String movieName = movie.getMovie_title();
        String moviePosterPath = movie.getPoster_path();
        String movieOverview = SYNOPSIS_LABEL_TEXT + movie.getOverview();
        String movieRating = RATING_LABEL_TEXT + movie.getVote_average();
        String movieReleaseDate = RELEASE_DATE_LABEL_TEXT + movie.getRelease_date();
        bindPoster(NetworkUtils.build_Picasso_Url(moviePosterPath));
        movieNameTextView.setText(movieName);
        movieOverviewTextView.setText(movieOverview);
        movieRatingTextView.setText(movieRating);
        movieRDTextView.setText(movieReleaseDate);
    }


    void  bindPoster(String moviePosterPath) {
        Picasso.with(this)
                .load(moviePosterPath)
                .into(movieImageView);
    }
}
