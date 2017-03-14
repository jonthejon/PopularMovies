package jonathanoliveira.org.popularmovies.data.utilities;

import org.json.JSONException;

import jonathanoliveira.org.popularmovies.core.beans.Movie;

/**
 * Created by JonathanOliveira on 09/03/17.
 */

public class UtilitiesHandler implements UtilitiesHandler_Interface {

    @Override
    public Movie[] convertJSONtoData(String rawJSONResult) {

        Movie[] movieResult;

        try {
            movieResult = JsonUtils.getObjectsFromJson(rawJSONResult);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return movieResult;

    }
}
