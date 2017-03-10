package jonathanoliveira.org.popularmovies.data.api;

import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToPresenter_Interface;

/**
 * Created by JonathanOliveira on 07/03/17.
 */

public interface APIHandler_Interface {

    void startAsyncTask(boolean searchOption);

    void serveAPIResult(String rawJSONResult);

    void serveAPIError();

    void bindPicassoToView(CoreToPresenter_Interface presenter, String moviePosterPath);

}
