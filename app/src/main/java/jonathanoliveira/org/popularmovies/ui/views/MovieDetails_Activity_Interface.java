package jonathanoliveira.org.popularmovies.ui.views;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by JonathanOliveira on 06/03/17.
 */

public interface MovieDetails_Activity_Interface {

    void wireViews();

    void bindDataToViews();

    Context getContext();

    ImageView getImageView();

    void setActionBar();

}
