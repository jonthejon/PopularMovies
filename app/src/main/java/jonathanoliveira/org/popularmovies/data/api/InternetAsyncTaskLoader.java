package jonathanoliveira.org.popularmovies.data.api;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;

/**
 * Created by JonathanOliveira on 11/03/17.
 */

public class InternetAsyncTaskLoader extends AsyncTaskLoader<String> {

    String rawJSONResult;
    private APIHandler_Interface apiHandler;
    Bundle args;

    public InternetAsyncTaskLoader(Context context, APIHandler_Interface apiHandler, Bundle args) {
        super(context);
        this.apiHandler = apiHandler;
        this.args = args;
    }


    @Override
    protected void onStartLoading() {

        if (args == null) {
            return;
        }
    }

    @Override
    public String loadInBackground() {
        boolean searchOption = args.getBoolean(apiHandler.getConstant());
        try {
            rawJSONResult = InternetConnection.getResponseFromHttpUrl(APIUtils.build_MD_API_Url(searchOption));

        } catch (IOException ioe) {
            apiHandler.serveAPIError();
//            ioe.printStackTrace();
        }
        return rawJSONResult;
    }

    @Override
    public void deliverResult(String data) {
        rawJSONResult = data;
        super.deliverResult(rawJSONResult);
    }
}
