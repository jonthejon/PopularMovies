package jonathanoliveira.org.popularmovies;

/**
 * Created by JonathanOliveira on 13/01/17.
 */

import android.content.ContentValues;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

/**
 * Utility functions to handle OpenWeatherMap JSON data.
 */
public final class JsonUtils {

    /**
     * This method parses JSON from a web response and returns an array of Strings
     * describing the weather over various days from the forecast.
     * <p/>
     * Later on, we'll be parsing the JSON into structured data within the
     * getFullWeatherDataFromJson function, leveraging the data we have stored in the JSON. For
     * now, we just convert the JSON into human-readable strings.
     *
     * @param forecastJsonStr JSON response from server
     *
     * @return Array of Strings describing weather data
     *
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static Movie[] getSimpleWeatherStringsFromJson(Context context, String forecastJsonStr)
            throws JSONException {

        final String RES_LIST = "results";


        final String FAIL_MESSAGE_CODE = "cod";
        Movie[] movies = null;

        JSONObject forecastJson = new JSONObject(forecastJsonStr);

        JSONArray moviesArray = forecastJson.getJSONArray(RES_LIST);

        movies = new Movie[moviesArray.length()];

        for (int i = 0; i < moviesArray.length(); i++) {

            String poster_path;
            String overview;
            String release_date;
            String title;
            double vote_average;

            /* Get the JSON object representing the current movie */
            JSONObject movieJsonObject = moviesArray.getJSONObject(i);


            poster_path = movieJsonObject.getString("poster_path");
            overview = movieJsonObject.getString("overview");
            release_date = movieJsonObject.getString("release_date");
            title = movieJsonObject.getString("title");
            vote_average = movieJsonObject.getDouble("vote_average");

            movies[i] = new Movie(poster_path,title,overview,release_date,vote_average);
        }

        return movies;
    }

    /**
     * Parse the JSON and convert it into ContentValues that can be inserted into our database.
     *
     * @param context         An application context, such as a service or activity context.
     * @param forecastJsonStr The JSON to parse into ContentValues.
     *
     * @return An array of ContentValues parsed from the JSON.
     */
    public static ContentValues[] getFullWeatherDataFromJson(Context context, String forecastJsonStr) {
        /** This will be implemented in a future lesson **/
        return null;
    }
}
