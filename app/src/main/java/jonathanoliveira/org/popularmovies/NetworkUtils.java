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
public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String MOVIEDB_API_BASE_URL =
            "https://api.themoviedb.org/3/discover/movie";

    private static final String MOVIEDB_PICASSO_BASE_URL =
            "http://image.tmdb.org/t/p/";

/*
    QUERY EXAMPLE TO THE API
    https://api.themoviedb.org/3/discover/movie?api_key=<<api_key>>&
    // sort_by=popularity.desc&include_adult=false&include_video=false&page=1
*/

    private static final String JONATHAN_MOVIEDB_API_KEY = "fb4244705257100efa4f14811cb5a0ed";

    // The sort type that we want to return
    private static final String popularityFormat = "popularity.desc";
    private static final String ratingFormat = "vote_average.desc";
    // The number of pages we want to return
    private static final int numPages = 1;
    private static final String negate = "false";

    // The API_key query name
    final static String API_KEY = "api_key";
    // The SORT TYPE query name
    final static String SORT_TYPE = "sort_by";
    // The ADULT FILM OPTION query name
    final static String INCLUDE_ADULT_FILM_OPTION = "include_adult";
    // The VIDEO OPTION query name
    final static String INCLUDE_VIDEO_OPTION = "include_video";
    // The NUMBER OF PAGES query name
    final static String PAGES_NUM = "page";
    // The image type of poster for picasso url
    final static String DOWNLOAD_IMAGE_TYPE = "w185";

    public static URL build_MD_API_Url(boolean sortByPopularity) {
        Uri uriQuery = Uri.parse(MOVIEDB_API_BASE_URL).buildUpon()
                .appendQueryParameter(API_KEY,JONATHAN_MOVIEDB_API_KEY)
                // completed: 16/01/17 apply a ternary to choose between the type of sort we will display
                .appendQueryParameter(SORT_TYPE,sortByPopularity ? popularityFormat : ratingFormat)
//                .appendQueryParameter(SORT_TYPE,popularityFormat)
                .appendQueryParameter(INCLUDE_ADULT_FILM_OPTION,negate)
                .appendQueryParameter(INCLUDE_VIDEO_OPTION,negate)
                .appendQueryParameter(PAGES_NUM,Integer.toString(numPages))
                .appendQueryParameter("year","2017")
                .build();
        URL url = null;
        try {
            url = new URL(uriQuery.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String build_Picasso_Url(String poster_path) {
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
    public static String getResponseFromHttpUrl(URL url) throws IOException {
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