package jonathanoliveira.org.popularmovies.ui.helpers;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

import jonathanoliveira.org.popularmovies.JsonUtils;
import jonathanoliveira.org.popularmovies.Movie;
import jonathanoliveira.org.popularmovies.NetworkUtils;

/**
 * Created by JonathanOliveira on 06/03/17.
 */

public class InternetAsyncTask extends AsyncTask<URL, Void, Movie[]> {

//    private GridAdapter mGridAdapter;

    public InternetAsyncTask(GridAdapter mGridAdapter) {
    }

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
//            mGridAdapter.setMoviesArr(movieResult);
        } else {
//            Toast.makeText(MovieGrid_Activity.this, "Something went terribly wrong! Please check your internet connection and try again.", Toast.LENGTH_LONG).show();
        }
    }
}
