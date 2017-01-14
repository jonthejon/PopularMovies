package jonathanoliveira.org.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView movieNameTextView;
    private TextView movieOverviewTextView;
    private TextView movieRDTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movieNameTextView = (TextView) findViewById(R.id.movie_name_detail);
        movieOverviewTextView = (TextView) findViewById(R.id.movie_overview_detail);
        movieRDTextView = (TextView) findViewById(R.id.movie_release_date_detail);

        Intent callerIntent = getIntent();

        if (callerIntent.hasExtra(Intent.EXTRA_TEXT)) {
            Movie callerMovie = (Movie) callerIntent.getSerializableExtra(Intent.EXTRA_TEXT);
            String movieName = callerMovie.getMovie_title();
            String movieOverview = callerMovie.getOverview();
            String movieReleaseDate = callerMovie.getRelease_date();
            movieNameTextView.setText(movieName);
            movieOverviewTextView.setText(movieOverview);
            movieRDTextView.setText(movieReleaseDate);
        }
    }
}
