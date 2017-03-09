package jonathanoliveira.org.popularmovies.data.utilities;

import jonathanoliveira.org.popularmovies.core.beans.Movie;

/**
 * Created by JonathanOliveira on 09/03/17.
 */

public interface UtilitiesHandler_Interface {

    Movie[] convertJSONtoData(String rawJSONResult);

}
