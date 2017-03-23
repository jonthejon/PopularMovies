package jonathanoliveira.org.popularmovies.ui.views;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.LoaderManager;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by JonathanOliveira on 06/03/17.
 */

public interface MovieDetails_Activity_Interface {

    void wireViews();

    void bindDataToViews();
    void bindTrailersToViews();
    void bindReviewsToViews();

    Context getContext();

    ImageView getImageView();

    void setFavoriteToggleButton();

    void setActionBar();

    LoaderManager getActivityLoaderManager();

    WindowManager getActivityWindowManager();

    PackageManager getActivityPackageManager();

    void startNewActivityWithIntent(Intent intent);

    ContentResolver getActivityContentResolver();

}
