package jonathanoliveira.org.popularmovies.data.api;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public final class APIUtils {

    private static final String TAG = APIUtils.class.getSimpleName();

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

    // The number of pages we want to return
    private static final int numPages = 1;

    private static final String language = "en-US";
    // The API_key query name
    private final static String API_KEY = "api_key";
    // The NUMBER OF PAGES query name
    private final static String PAGES_NUM = "page";
    private final static String LANGUAGE = "language";
    // The image type of poster for picasso url
//    private final static String DOWNLOAD_IMAGE_TYPE = "w185";
    private final static String DOWNLOAD_IMAGE_TYPE = "w342";
//    private final static String DOWNLOAD_IMAGE_TYPE = "w500";
//    private final static String DOWNLOAD_IMAGE_TYPE = "w780";

    public static URL build_MD_API_Url(boolean sortByPopularity) {
        Uri uriQuery = Uri.parse(sortByPopularity ? MOVIEDB_PICASSO_POPULAR_URL: MOVIEDB_PICASSO_RATED_URL).buildUpon()
                .appendQueryParameter(API_KEY,JONATHAN_MOVIEDB_API_KEY)
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

    public static String build_Picasso_Url(String poster_path) {
        String url = MOVIEDB_PICASSO_BASE_URL;
        url += DOWNLOAD_IMAGE_TYPE;
        url += poster_path;
        return url;
    }
}