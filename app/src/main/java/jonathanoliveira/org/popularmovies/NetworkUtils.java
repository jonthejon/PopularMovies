package jonathanoliveira.org.popularmovies;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the weather servers.
 */
final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

//    private static final String MOVIEDB_API_BASE_URL =
//            "https://api.themoviedb.org/3/discover/movie";

    private static final String MOVIEDB_PICASSO_BASE_URL =
            "http://image.tmdb.org/t/p/";

    private static final String MOVIEDB_PICASSO_POPULAR_URL =
            "https://api.themoviedb.org/3/movie/popular";
    private static final String MOVIEDB_PICASSO_RATED_URL =
            "https://api.themoviedb.org/3/movie/top_rated";



/*
    QUERY EXAMPLE TO THE API
    https://api.themoviedb.org/3/movie/popular?api_key=fb4244705257100efa4f14811cb5a0ed&language=en-US&page=1
    https://api.themoviedb.org/3/movie/top_rated?api_key=fb4244705257100efa4f14811cb5a0ed&language=en-US&page=1
*/

    private static final String JONATHAN_MOVIEDB_API_KEY = "fb4244705257100efa4f14811cb5a0ed";

    // The sort type that we want to return
//    private static final String popularityFormat = "popularity.desc";
//    private static final String ratingFormat = "vote_average.desc";
    // The number of pages we want to return
    private static final int numPages = 1;
//    private static final String negate = "false";
    private static final String language = "en-US";

    // The API_key query name
    private final static String API_KEY = "api_key";
    // The SORT TYPE query name
//    final static String SORT_TYPE = "sort_by";
    // The ADULT FILM OPTION query name
//    final static String INCLUDE_ADULT_FILM_OPTION = "include_adult";
    // The VIDEO OPTION query name
//    final static String INCLUDE_VIDEO_OPTION = "include_video";
    // The NUMBER OF PAGES query name
    private final static String PAGES_NUM = "page";
    private final static String LANGUAGE = "language";
    // The image type of poster for picasso url
    private final static String DOWNLOAD_IMAGE_TYPE = "w185";

    static URL build_MD_API_Url(boolean sortByPopularity) {
        Uri uriQuery = Uri.parse(sortByPopularity ? MOVIEDB_PICASSO_POPULAR_URL: MOVIEDB_PICASSO_RATED_URL).buildUpon()
                .appendQueryParameter(API_KEY,JONATHAN_MOVIEDB_API_KEY)
                // completed: 16/01/17 apply a ternary to choose between the type of sort we will display
                .appendQueryParameter(LANGUAGE,language)
                .appendQueryParameter(PAGES_NUM,Integer.toString(numPages))
                .build();
        URL url = null;
        try {
            url = new URL(uriQuery.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        return url;
    }

    static String build_Picasso_Url(String poster_path) {
        String url = MOVIEDB_PICASSO_BASE_URL;
        url += DOWNLOAD_IMAGE_TYPE;
        url += poster_path;
        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}