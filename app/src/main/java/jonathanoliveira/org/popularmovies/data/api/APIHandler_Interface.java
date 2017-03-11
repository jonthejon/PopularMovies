package jonathanoliveira.org.popularmovies.data.api;

import android.os.Bundle;

import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToAdapter_Interface;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToPresenter_Interface;

/**
 * Created by JonathanOliveira on 07/03/17.
 */

public interface APIHandler_Interface {

    void startAsyncTask(boolean searchOption);

    void startAsyncTaskLoader(CoreToAdapter_Interface adapter, boolean searchOption);

    void fireUpLoader(CoreToAdapter_Interface adapter, Bundle bundle);

    void serveAPIResult(String rawJSONResult);

    void serveAPIError();

    void bindPicassoToView(CoreToPresenter_Interface presenter, String moviePosterPath);

    String getConstant();

    Bundle createBundle(boolean searchOption);

}
