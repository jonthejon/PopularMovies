package jonathanoliveira.org.popularmovies.data.utilities;

import org.json.JSONException;

import jonathanoliveira.org.popularmovies.core.beans.Movie;

/**
 * Created by JonathanOliveira on 09/03/17.
 */

public class UtilitiesHandler implements UtilitiesHandler_Interface {

    @Override
    public Movie[] convertJSONtoMoviesArr(String rawJSONResult) {
        Movie[] movieResult;
        try {
            movieResult = JsonUtils.getMoviesArrFromJson(rawJSONResult);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return movieResult;
    }

    @Override
    public String[][] convertJSONtoTrailerArr(String rawJSONResult) {
        String[][] trailerResult;
        try {
            trailerResult = JsonUtils.convertJSONtoTrailerArr(rawJSONResult);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return trailerResult;
    }

    @Override
    public String[][] convertJSONtoReviewArr(String rawJSONResult) {
        String[][] reviewResult;
        try {
            reviewResult = JsonUtils.convertJSONtoReviewArr(rawJSONResult);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return reviewResult;
    }
}
