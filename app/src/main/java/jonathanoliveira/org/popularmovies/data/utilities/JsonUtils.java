package jonathanoliveira.org.popularmovies.data.utilities;

/**
 * Created by JonathanOliveira on 13/01/17.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import jonathanoliveira.org.popularmovies.core.beans.Movie;

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
     * @param jsonString JSON response from server
     *
     * @return Array of Strings describing weather data
     *
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static Movie[] getMoviesArrFromJson(String jsonString)
            throws JSONException {

        final String RES_LIST = "results";

//        Log.d(TAG, "getObjectsFromJson: init");

//        final String FAIL_MESSAGE_CODE = "cod";
        Movie[] movies;

        JSONObject forecastJson = new JSONObject(jsonString);

        JSONArray moviesArray = forecastJson.getJSONArray(RES_LIST);

        movies = new Movie[moviesArray.length()];
//        Log.d(TAG, "getObjectsFromJson: " + movies.length);

        for (int i = 0; i < moviesArray.length(); i++) {

            String poster_path;
            String overview;
            String release_date;
            String title;
            double vote_average;
            int movie_id;

            /* Get the JSON object representing the current movie */
            JSONObject movieJsonObject = moviesArray.getJSONObject(i);


            poster_path = movieJsonObject.getString("poster_path");
            overview = movieJsonObject.getString("overview");
            release_date = movieJsonObject.getString("release_date").substring(0,4);
            title = movieJsonObject.getString("title");
            vote_average = movieJsonObject.getDouble("vote_average");
            movie_id = movieJsonObject.getInt("id");
//            Log.d(TAG, "getObjectsFromJson: " + movie_id);
//            movies[i] = new Movie(poster_path,title,overview,release_date,vote_average);
//            movies[i] = new Movie(poster_path,title,overview,release_date,vote_average,movie_id);
            movies[i] = new Movie.MovieBuilder(movie_id)
                    .poster_path(poster_path)
                    .movie_title(title)
                    .overview(overview)
                    .release_date(release_date)
                    .vote_average(vote_average)
                    .build();
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
/*    static ContentValues[] getFullWeatherDataFromJson(Context context, String forecastJsonStr) {
        *//** This will be implemented in a future lesson **//*
        return null;
    }*/

    public static String[][] convertJSONtoTrailerArr(String jsonString)
            throws JSONException {

        final String RES_LIST = "results";

        String[][] movie_trailers;

        JSONObject resultJson = new JSONObject(jsonString);

        JSONArray trailersArray = resultJson.getJSONArray(RES_LIST);

        ArrayList<String[]> youtubeTrailers = new ArrayList<>();

        for (int i = 0; i < trailersArray.length(); i++) {

            String[] validTrailer = new String[2];
            String name;
            String key;
            String siteType;

            /* Get the JSON object representing the current movie */
            JSONObject trailerJsonObject = trailersArray.getJSONObject(i);

            siteType = trailerJsonObject.getString("site").toLowerCase();

            if (!siteType.equals("youtube")) continue;

            name = trailerJsonObject.getString("name");
            key = trailerJsonObject.getString("key");

            validTrailer[0] = name;
            validTrailer[1] = key;
            youtubeTrailers.add(validTrailer);

        }

        movie_trailers = youtubeTrailers.size() <= 3 ? new String[youtubeTrailers.size()][2] : new String[3][2];
//        movie_trailers = new String[youtubeTrailers.size()][2];

        for (int i = 0; i < youtubeTrailers.size(); i++) {
            movie_trailers[i] = youtubeTrailers.get(i);
            if (i == 2) {
//                Log.d("JONATHAN", "gotChopped: ");
                return movie_trailers;
            }
        }
//        Log.d("JONATHAN", "1 trailer is: " + movie_trailers[0][1]);
        return movie_trailers;
    }

    public static String[][] convertJSONtoReviewArr(String jsonString)
            throws JSONException {

        final String RES_LIST = "results";

        String[][] movie_reviews;

        JSONObject resultJson = new JSONObject(jsonString);

        JSONArray reviewsArray = resultJson.getJSONArray(RES_LIST);

//        movie_reviews = new String[reviewsArray.length()][2];
        movie_reviews = reviewsArray.length() <= 3 ? new String[reviewsArray.length()][2] : new String[3][2];

        for (int i = 0; i < reviewsArray.length(); i++) {

            if (i >= 3) {
                return movie_reviews;
            }

            String[] validReview = new String[2];
            String author;
            String content;

            /* Get the JSON object representing the current movie */
            JSONObject reviewJsonObject = reviewsArray.getJSONObject(i);

            author = reviewJsonObject.getString("author");
            content = reviewJsonObject.getString("content");

            validReview[0] = author;
            validReview[1] = content;

            movie_reviews[i] = validReview;
        }

        return movie_reviews;
    }

}
