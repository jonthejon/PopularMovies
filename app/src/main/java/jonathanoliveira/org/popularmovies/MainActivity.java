package jonathanoliveira.org.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

// TODO: 13/01/17 implement the interface that is inside the GridAdapter class and override the necessary method
// TODO: 13/01/17 within the implemented method, create an Intent and start the new Activity
public class MainActivity extends AppCompatActivity implements GridAdapter.GridItemClickListener {

    private RecyclerView mRecyclerView;
    private GridAdapter mGridAdapter;
    private Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadMoviesData();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_grid);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager gridManager = new GridLayoutManager(getApplicationContext(),2);
        mRecyclerView.setLayoutManager(gridManager);
        mRecyclerView.setHasFixedSize(true);
        Picasso.Builder picassoBuilder = new Picasso.Builder(this);
        this.picasso = picassoBuilder.build();
        mGridAdapter = new GridAdapter(this, this.picasso);
        mRecyclerView.setAdapter(mGridAdapter);
    }

    public void loadMoviesData() {
        new internetAsyncTask().execute(NetworkUtils.build_MD_API_Url());
    }


    @Override
    public void OnClickView(String movieName) {
        Toast.makeText(this,movieName,Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(this,MovieDetailsActivity.class);
//        startActivity(intent);
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
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}