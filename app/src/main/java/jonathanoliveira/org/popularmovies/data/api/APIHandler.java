package jonathanoliveira.org.popularmovies.data.api;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.widget.ImageView;

import jonathanoliveira.org.popularmovies.core.Core;
import jonathanoliveira.org.popularmovies.core.Core_Interface;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToAdapter_Interface;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToPresenter_Interface;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToViewHolder_Interface;
import jonathanoliveira.org.popularmovies.data.manager.Manager;

/**
 * Created by JonathanOliveira on 07/03/17.
 */

public class APIHandler implements APIHandler_Interface, LoaderManager.LoaderCallbacks<String> {

    private static final String SEARCH_QUERY_BOOL_EXTRA = "bool_option";
    private static final String CHANNEL_NUMBER_EXTRA = "channel";
    private static final String MOVIE_ID_EXTRA = "channel";
    private static final int ADAPTER_LOADER_ID = 22;
    private static final int TRAILERS_LOADER_ID = 23;
    private static final int REVIEWS_LOADER_ID = 24;

    public APIHandler() {
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {

        InternetAsyncTaskLoader loader = null;

        switch (id) {
            case 22:
                loader = new InternetAsyncTaskLoader(Core.getCoreInstance().getAdapterPresenterInstance().getContext(), this, args, id);
                break;
            default:
                loader = new InternetAsyncTaskLoader(Core.getCoreInstance().getPresenterInstance().getContext(), this, args, id);
                break;
        }
        return loader;
//        return new InternetAsyncTaskLoader(Core.getCoreInstance().getAdapterPresenterInstance().getContext(), this, args);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String rawJSONResult) {
        if (rawJSONResult != null) {
            serveAPIResult(rawJSONResult, loader.getId());
        } else {
            serveAPIError();
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    @Override
    public void startAsyncTask(boolean searchOption) {
        new InternetAsyncTask(this).execute(APIUtils.build_Movies_Array_Url(searchOption));
    }

/*    @Override
    public void startAsyncTaskLoader(CoreToAdapter_Interface adapter, boolean searchOption) {
//        adapter.getLoaderManager().initLoader(LOADER_ID, null, this);
        fireUpLoader(adapter, createBundle(searchOption));
    }*/

    @Override
    public void startAsyncTaskLoader(int channel) {

        Core_Interface core = Core.getCoreInstance();
        LoaderManager loaderManager;
        Bundle bundle = new Bundle();

        switch (channel) {
            case 29:
                CoreToAdapter_Interface adapter = core.getAdapterPresenterInstance();
//                boolean searchOption = adapter.getBooleanOption();
                loaderManager = adapter.getLoaderManager();
//                bundle.putInt(CHANNEL_NUMBER_EXTRA,channel);
                bundle.putBoolean(SEARCH_QUERY_BOOL_EXTRA, adapter.getBooleanOption());
//                fireUpLoader(loaderManager, createBundle(searchOption));
                fireUpLoader(loaderManager, bundle, ADAPTER_LOADER_ID);
                break;

            case 41:
                CoreToPresenter_Interface trailer_presenter = core.getPresenterInstance();
                loaderManager = trailer_presenter.getLoaderManager();
                bundle.putInt(MOVIE_ID_EXTRA,trailer_presenter.getMovieId());
                fireUpLoader(loaderManager, bundle, TRAILERS_LOADER_ID);
                break;

            case 43:
                CoreToPresenter_Interface review_presenter = core.getPresenterInstance();
                loaderManager = review_presenter.getLoaderManager();
                bundle.putInt(MOVIE_ID_EXTRA,review_presenter.getMovieId());
                fireUpLoader(loaderManager, bundle, REVIEWS_LOADER_ID);
                break;
        }

    }

/*    @Override
    public void fireUpLoader(CoreToAdapter_Interface adapter, Bundle bundle) {
        LoaderManager loaderManager = adapter.getLoaderManager();
        Loader<String> loader = loaderManager.getLoader(LOADER_ID);
        if (loader == null) {
            loaderManager.initLoader(LOADER_ID, bundle, this).forceLoad();
        } else {
            loaderManager.restartLoader(LOADER_ID, bundle, this).forceLoad();
        }
    }*/

    @Override
    public void fireUpLoader(LoaderManager loaderManager, Bundle bundle, int loader_id) {
        Loader<String> loader = loaderManager.getLoader(loader_id);
        if (loader == null) {
            loaderManager.initLoader(loader_id, bundle, this).forceLoad();
        } else {
            loaderManager.restartLoader(loader_id, bundle, this).forceLoad();
        }
    }

    @Override
    public Bundle createBundle(boolean searchOption) {
        Bundle loaderBundle = new Bundle();
        loaderBundle.putBoolean(SEARCH_QUERY_BOOL_EXTRA, searchOption);
        return loaderBundle;
    }

    @Override
    public Bundle createBundle(int channel) {
        Bundle loaderBundle = new Bundle();
//        loaderBundle.putBoolean(SEARCH_QUERY_BOOL_EXTRA, searchOption);
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
    public void bindPicassoToView(CoreToViewHolder_Interface viewHolder, String moviePosterPath) {
        String moviePosterURLString = APIUtils.build_Picasso_Url(moviePosterPath);
        Context activityContext = viewHolder.getContext();
        ImageView posterView = viewHolder.getImageView();
        new PicassoHandler().bindPicassoToView(activityContext, moviePosterURLString, posterView);
    }

/*    @Override
    public void serveAPIResult(String rawJSONResult) {
        Manager.getManagerInstance().dataAPICallback(rawJSONResult);
    }*/

    @Override
    public void serveAPIResult(String rawJSONResult, int loader_id) {
        Manager.getManagerInstance().dataAPICallback(rawJSONResult, loader_id);
    }

    @Override
    public void serveAPIError() {
        Manager.getManagerInstance().errorAPICallback();
    }

    @Override
    public String getBoolConstant() {
        return SEARCH_QUERY_BOOL_EXTRA;
    }

    @Override
    public String getMovieIDConstant() {
        return MOVIE_ID_EXTRA;
    }
}
