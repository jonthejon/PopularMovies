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
    private int loader_id;

    public InternetAsyncTaskLoader(Context context, APIHandler_Interface apiHandler, Bundle args, int loader_id) {
        super(context);
        this.apiHandler = apiHandler;
        this.args = args;
        this.loader_id = loader_id;
    }


    @Override
    protected void onStartLoading() {

        if (args == null) {
            return;
        }
    }

    @Override
    public String loadInBackground() {
        switch (this.loader_id) {
            case 22:
                boolean searchOption = args.getBoolean(apiHandler.getBoolConstant());
                try {
                    rawJSONResult = InternetConnection.getResponseFromHttpUrl(APIUtils.build_Movies_Array_Url(searchOption));
                } catch (IOException ioe) {
                    apiHandler.serveAPIError();
                }
                break;

            case 23:
                try {
                    int movie_id = args.getInt(apiHandler.getMovieIDConstant());
                    rawJSONResult = InternetConnection.getResponseFromHttpUrl(APIUtils.build_Trailers_Url(movie_id));
                } catch (IOException ioe) {
                    apiHandler.serveAPIError();
                }
                break;

            case 24:
                try {
                    int movie_id = args.getInt(apiHandler.getMovieIDConstant());
                    rawJSONResult = InternetConnection.getResponseFromHttpUrl(APIUtils.build_Reviews_Url(movie_id));
                } catch (IOException ioe) {
                    apiHandler.serveAPIError();
                }
                break;
        }
//        boolean searchOption = args.getBoolean(apiHandler.getConstant());
//        try {
//            rawJSONResult = InternetConnection.getResponseFromHttpUrl(APIUtils.build_Movies_Array_Url(searchOption));
//
//        } catch (IOException ioe) {
//            apiHandler.serveAPIError();
////            ioe.printStackTrace();
//        }
        return rawJSONResult;
    }

    @Override
    public void deliverResult(String data) {
        rawJSONResult = data;
        super.deliverResult(rawJSONResult);
    }
}
