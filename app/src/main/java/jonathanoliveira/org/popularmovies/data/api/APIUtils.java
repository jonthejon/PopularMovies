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

    private static final String MOVIEDB_BASIC_URL = "https://api.themoviedb.org/3/movie/";

    private static final String YOUTUBE_BASIC_URL = "https://www.youtube.com/watch";

//    private static final String MOVIEDB_PICASSO_POPULAR_URL =
//            "https://api.themoviedb.org/3/movie/popular";
    private static final String MOVIEDB_PICASSO_POPULAR_URL = MOVIEDB_BASIC_URL + "popular";
//    private static final String MOVIEDB_PICASSO_RATED_URL =
//            "https://api.themoviedb.org/3/movie/top_rated";
    private static final String MOVIEDB_PICASSO_RATED_URL = MOVIEDB_BASIC_URL + "top_rated";

    private static final String TRAILER_APPENDIX = "/videos";
    private static final String REVIEWS_APPENDIX = "/reviews";


/*
    QUERY EXAMPLE TO THE API
    https://api.themoviedb.org/3/movie/popular?api_key=fb4244705257100efa4f14811cb5a0ed&language=en-US&page=1
    https://api.themoviedb.org/3/movie/top_rated?api_key=fb4244705257100efa4f14811cb5a0ed&language=en-US&page=1
*/

    private static final String JONATHAN_MOVIEDB_API_KEY = "fb4244705257100efa4f14811cb5a0ed";
//    BuildConfig.GET

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

    private final static String YOUTUBE_PARAM = "v";

    public static URL build_Movies_Array_Url(boolean sortByPopularity) {
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

    public static URL build_Trailers_Url(int movie_id) {
        String basicTrailerString = MOVIEDB_BASIC_URL + movie_id + TRAILER_APPENDIX;
        Uri uriQuery = Uri.parse(basicTrailerString).buildUpon()
                .appendQueryParameter(API_KEY,JONATHAN_MOVIEDB_API_KEY)
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

    public static URL build_Reviews_Url(int movie_id) {
        String basicTrailerString = MOVIEDB_BASIC_URL + movie_id + REVIEWS_APPENDIX;
        Uri uriQuery = Uri.parse(basicTrailerString).buildUpon()
                .appendQueryParameter(API_KEY,JONATHAN_MOVIEDB_API_KEY)
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

    public static String build_Youtube_Url(String youtubeKey) {
        Uri uriQuery = Uri.parse(YOUTUBE_BASIC_URL).buildUpon()
                .appendQueryParameter(YOUTUBE_PARAM,youtubeKey)
                .build();
        return uriQuery.toString();
    }
}