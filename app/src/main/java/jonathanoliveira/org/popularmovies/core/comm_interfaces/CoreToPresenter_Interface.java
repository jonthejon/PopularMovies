package jonathanoliveira.org.popularmovies.core.comm_interfaces;

import android.content.Context;
import android.support.v4.app.LoaderManager;
import android.widget.ImageView;

/**
 * Created by JonathanOliveira on 10/03/17.
 */

public interface CoreToPresenter_Interface {

    Context getContext();

    ImageView getImageView();

    String getString();

    LoaderManager getLoaderManager();

    int getMovieId();

    void bindTrailerstoViews(String[][] trailers);

}
