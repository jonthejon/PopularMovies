package jonathanoliveira.org.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView movieNameTextView;
    private ImageView movieImageView;
    private TextView movieOverviewTextView;
    private TextView movieRatingTextView;
    private TextView movieRDTextView;

    private Movie callerMovie;

    private final String RATING_LABEL_TEXT = "User rating: ";
    private final String RELEASE_DATE_LABEL_TEXT = "Release date: ";
    private final String SYNOPSIS_LABEL_TEXT = "Plot Synopsis: \n";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movieNameTextView = (TextView) findViewById(R.id.movie_name_detail);
        movieImageView = (ImageView) findViewById(R.id.poster_detail);
        movieOverviewTextView = (TextView) findViewById(R.id.movie_overview_detail);
        movieRatingTextView = (TextView) findViewById(R.id.movie_rating_detail);
        movieRDTextView = (TextView) findViewById(R.id.movie_release_date_detail);

        Intent callerIntent = getIntent();

                if (callerIntent.hasExtra(Intent.EXTRA_TEXT)) {
                    callerMovie = (Movie) callerIntent.getSerializableExtra(Intent.EXTRA_TEXT);
                    wireViews();
        }
    }

    void wireViews() {
        String movieName = callerMovie.getMovie_title();
        String moviePosterPath = callerMovie.getPoster_path();
        String movieOverview = SYNOPSIS_LABEL_TEXT + callerMovie.getOverview();
        String movieRating = RATING_LABEL_TEXT + callerMovie.getVote_average();
        String movieReleaseDate = RELEASE_DATE_LABEL_TEXT + callerMovie.getRelease_date();
        wirePoster(NetworkUtils.build_Picasso_Url(moviePosterPath));
        movieNameTextView.setText(movieName);
        movieOverviewTextView.setText(movieOverview);
        movieRatingTextView.setText(movieRating);
        movieRDTextView.setText(movieReleaseDate);
    }

    void  wirePoster(String moviePosterPath) {
        Picasso.with(this)
                .load(moviePosterPath)
                .into(movieImageView);
    }
}
