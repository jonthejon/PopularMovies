package jonathanoliveira.org.popularmovies.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

import jonathanoliveira.org.popularmovies.GridAdapter;
import jonathanoliveira.org.popularmovies.JsonUtils;
import jonathanoliveira.org.popularmovies.Movie;
import jonathanoliveira.org.popularmovies.NetworkUtils;
import jonathanoliveira.org.popularmovies.R;

public class MovieGrid_Activity extends AppCompatActivity implements GridAdapter.GridItemClickListener {

    private RecyclerView mRecyclerView;
    private GridAdapter mGridAdapter;
    private Picasso picasso;
    private boolean sortByPopularity = true;
    // completed: 16/01/17 implement the boolean that will inform which type of sort will be displayed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadMoviesData(sortByPopularity);
        String test = "test";
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_grid);
        RecyclerView.LayoutManager gridManager;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridManager = new GridLayoutManager(getApplicationContext(), 2);
        } else {
            gridManager = new GridLayoutManager(getApplicationContext(), 4);
        }
        mRecyclerView.setLayoutManager(gridManager);
        mRecyclerView.setHasFixedSize(true);
        Picasso.Builder picassoBuilder = new Picasso.Builder(this);
        this.picasso = picassoBuilder.build();
        mGridAdapter = new GridAdapter(this, this.picasso);
        mRecyclerView.setAdapter(mGridAdapter);
    }

    // completed: 16/01/17 change loadMoviesData() and NetworkUtils.build_MD_API_Url() to receive a boolean concerning the type of information
    public void loadMoviesData(boolean sortByPopularity) {
        new InternetAsyncTask().execute(NetworkUtils.build_MD_API_Url(sortByPopularity));
    }

    @Override
    public void OnClickView(int position) {
        Movie movie = mGridAdapter.getMovie(position);
        Intent intent = new Intent(this, MovieDetails_Activity.class);
        intent.putExtra(Intent.EXTRA_TEXT, movie);
        startActivity(intent);
    }


    public class InternetAsyncTask extends AsyncTask<URL, Void, Movie[]> {
        @Override
        protected Movie[] doInBackground(URL... urls) {
            if (urls.length == 0 || urls[0] == null) {
                return null;
            }

            String rawJSONResult;
            Movie[] movieResult = null;

            try {
                rawJSONResult = NetworkUtils.getResponseFromHttpUrl(urls[0]);
                movieResult = JsonUtils.getSimpleWeatherStringsFromJson(rawJSONResult);

            } catch (IOException | JSONException ioe) {
                ioe.printStackTrace();
            }
            return movieResult;
        }

        @Override
        protected void onPostExecute(Movie[] movieResult) {
            if (movieResult != null) {
                mGridAdapter.setMoviesArr(movieResult);
            } else {
                Toast.makeText(MovieGrid_Activity.this, "Something went terribly wrong! Please check your internet connection and try again.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.settings_button:
                if (this.sortByPopularity) {
                    item.setTitle(getString(R.string.popular));
                    this.sortByPopularity = false;
                } else {
                    item.setTitle(getString(R.string.rated));
                    this.sortByPopularity = true;
                }
                loadMoviesData(this.sortByPopularity);
                // completed: 16/01/17 every time this button is pressed, change the title to the other option and the boolean too
                // completed: 16/01/17 call loadInfo again
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}