package jonathanoliveira.org.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView movieNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movieNameTextView = (TextView) findViewById(R.id.movie_name_detail);

        Intent callerIntent = getIntent();

        if (callerIntent.hasExtra(Intent.EXTRA_TEXT)) {
            Movie callerMovie = (Movie) callerIntent.getSerializableExtra(Intent.EXTRA_TEXT);
            String movieName = callerMovie.getMovie_title();
            movieNameTextView.setText(movieName);
        }
    }
}
