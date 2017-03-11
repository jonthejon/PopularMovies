package jonathanoliveira.org.popularmovies.data.api;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

/**
 * Created by JonathanOliveira on 06/03/17.
 */

public class InternetAsyncTask extends AsyncTask<URL, Void, String> {

    private APIHandler_Interface apiHandler;

    public InternetAsyncTask(APIHandler_Interface apiHandler) {
        this.apiHandler = apiHandler;
    }

    @Override
    protected String doInBackground(URL... urls) {
        if (urls.length == 0 || urls[0] == null) {
            return null;
        }

        String rawJSONResult = null;

        try {
            rawJSONResult = InternetConnection.getResponseFromHttpUrl(urls[0]);

        } catch (IOException ioe) {
            apiHandler.serveAPIError();
//            ioe.printStackTrace();
        }
        return rawJSONResult;
    }

    @Override
    protected void onPostExecute(String rawJSONResult) {
        if (rawJSONResult != null) {
            apiHandler.serveAPIResult(rawJSONResult);
        } else {
            apiHandler.serveAPIError();
        }
    }
}