package jonathanoliveira.org.popularmovies.data.api;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.widget.ImageView;

import jonathanoliveira.org.popularmovies.core.Core;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToAdapter_Interface;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToPresenter_Interface;
import jonathanoliveira.org.popularmovies.data.manager.Manager;

/**
 * Created by JonathanOliveira on 07/03/17.
 */

public class APIHandler implements APIHandler_Interface, LoaderManager.LoaderCallbacks<String> {

    private static final String SEARCH_QUERY_BOOL_EXTRA = "bool_option";
    private static final int LOADER_ID = 22;

    public APIHandler() {
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new InternetAsyncTaskLoader(Core.getCoreInstance().getAdapterPresenterInstance().getContext(), this, args);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String rawJSONResult) {
        if (rawJSONResult != null) {
            serveAPIResult(rawJSONResult);
        } else {
            serveAPIError();
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    @Override
    public void startAsyncTask(boolean searchOption) {
        new InternetAsyncTask(this).execute(APIUtils.build_MD_API_Url(searchOption));
    }

    @Override
    public void startAsyncTaskLoader(CoreToAdapter_Interface adapter, boolean searchOption) {
//        adapter.getLoaderManager().initLoader(LOADER_ID, null, this);
        fireUpLoader(adapter, createBundle(searchOption));
    }

    @Override
    public void fireUpLoader(CoreToAdapter_Interface adapter, Bundle bundle) {
        LoaderManager loaderManager = adapter.getLoaderManager();
        Loader<String> loader = loaderManager.getLoader(LOADER_ID);
        if (loader == null) {
            loaderManager.initLoader(LOADER_ID, bundle, this).forceLoad();
        } else {
            loaderManager.restartLoader(LOADER_ID, bundle, this).forceLoad();
        }
    }

    @Override
    public Bundle createBundle(boolean searchOption) {
        Bundle loaderBundle = new Bundle();
        loaderBundle.putBoolean(SEARCH_QUERY_BOOL_EXTRA, searchOption);
        return loaderBundle;
    }

    @Override
    public void bindPicassoToView(CoreToPresenter_Interface presenter, String moviePosterPath) {
        String moviePosterURLString = APIUtils.build_Picasso_Url(moviePosterPath);
        Context activityContext = presenter.getContext();
        ImageView posterView = presenter.getImageView();
        new PicassoHandler().bindPicassoToView(activityContext, moviePosterURLString, posterView);
    }

    @Override
    public void serveAPIResult(String rawJSONResult) {
        Manager.getManagerInstance().dataAPICallback(rawJSONResult);
    }

    @Override
    public void serveAPIError() {
        Manager.getManagerInstance().errorAPICallback();
    }

    @Override
    public String getConstant() {
        return SEARCH_QUERY_BOOL_EXTRA;
    }
}
