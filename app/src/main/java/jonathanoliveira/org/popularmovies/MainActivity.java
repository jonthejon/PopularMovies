package jonathanoliveira.org.popularmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private GridAdapter mGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadMoviesData();
        mGridAdapter = new GridAdapter(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_grid);
        mRecyclerView.setHasFixedSize(true);
        // TODO: 13/01/17 change the type of layout to LinearLayout and test the app. Commit.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        RecyclerView.LayoutManager gridManager = new GridLayoutManager(getApplicationContext(),2);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mGridAdapter);
    }

    public void loadMoviesData() {
        new internetAsyncTask().execute(NetworkUtils.build_MD_API_Url());
    }

    public class internetAsyncTask extends AsyncTask<URL, Void, Movie[]> {
        @Override
        protected Movie[] doInBackground(URL... urls) {
            if (urls.length == 0) {
                return null;
            }

            String rawJSONResult;
            Movie[] movieResult = null;
            try {
                rawJSONResult = NetworkUtils.getResponseFromHttpUrl(urls[0]);
                movieResult = JsonUtils.getSimpleWeatherStringsFromJson(MainActivity.this,rawJSONResult);

            } catch (IOException | JSONException ioe) {
                ioe.printStackTrace();
            }
            return movieResult;
        }

        @Override
        protected void onPostExecute(Movie[] movieResult) {
            if (movieResult != null) {
                mGridAdapter.setMoviesArr(movieResult);
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
//                Toast.makeText(this,"Settings button clicked.", Toast.LENGTH_LONG).show();
//                String url = NetworkUtils.build_MD_API_Url().toString();
//                Toast.makeText(this,url,Toast.LENGTH_LONG).show();
//                Toast.makeText(this, Integer.toString(testMovies.length), Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}