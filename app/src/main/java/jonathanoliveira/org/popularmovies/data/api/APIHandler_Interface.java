package jonathanoliveira.org.popularmovies.data.api;

import android.support.v4.app.LoaderManager;
import android.os.Bundle;

import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToPresenter_Interface;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToViewHolder_Interface;

/**
 * Created by JonathanOliveira on 07/03/17.
 */

public interface APIHandler_Interface {

    void startAsyncTask(boolean searchOption);

//    void startAsyncTaskLoader(CoreToAdapter_Interface adapter, boolean searchOption);
    void startAsyncTaskLoader(int channel);

//    void fireUpLoader(CoreToAdapter_Interface adapter, Bundle bundle);
    void fireUpLoader(LoaderManager loaderManager, Bundle bundle, int loader_id);

//    void serveAPIResult(String rawJSONResult);
    void serveAPIResult(String rawJSONResult, int loader_id);

    void serveAPIError();

    void bindPicassoToView(CoreToPresenter_Interface presenter, String moviePosterPath);
    void bindPicassoToView(CoreToViewHolder_Interface viewHolder, String moviePosterPath);

    String getBoolConstant();
    String getMovieIDConstant();

    Bundle createBundle(boolean searchOption);
    Bundle createBundle(int channel);

}
