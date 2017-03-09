package jonathanoliveira.org.popularmovies.data.api;

import jonathanoliveira.org.popularmovies.data.manager.Manager;

/**
 * Created by JonathanOliveira on 07/03/17.
 */

public class APIHandler implements APIHandler_Interface {

    public APIHandler() {
    }

    @Override
    public void startAsyncTask(boolean searchOption) {
        new InternetAsyncTask(this).execute(APIUtils.build_MD_API_Url(searchOption));
    }

    @Override
    public void serveAPIResult(String rawJSONResult) {
        Manager.getManagerInstance().dataAPICallback(rawJSONResult);
    }

    @Override
    public void serveAPIError() {
        Manager.getManagerInstance().errorAPICallback();
    }
}
