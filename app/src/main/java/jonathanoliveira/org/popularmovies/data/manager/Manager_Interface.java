package jonathanoliveira.org.popularmovies.data.manager;

import jonathanoliveira.org.popularmovies.core.beans.Movie;

/**
 * Created by JonathanOliveira on 09/03/17.
 */

public interface Manager_Interface {

    void dataAPICallback(String rawJSONResult);

    void errorAPICallback();

    Movie[] getObjectFromJSON(String rawJSONResult);

}
