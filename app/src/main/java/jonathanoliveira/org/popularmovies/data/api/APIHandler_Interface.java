package jonathanoliveira.org.popularmovies.data.api;

/**
 * Created by JonathanOliveira on 07/03/17.
 */

public interface APIHandler_Interface {

    void startAsyncTask(boolean searchOption);

    void serveAPIResult(String rawJSONResult);

    void serveAPIError();

}
