package jonathanoliveira.org.popularmovies.data.api;

import android.content.Context;
import android.widget.ImageView;

import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToPresenter_Interface;
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

}
