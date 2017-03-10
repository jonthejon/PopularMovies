package jonathanoliveira.org.popularmovies.ui.presenters;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import jonathanoliveira.org.popularmovies.core.beans.Movie;
import jonathanoliveira.org.popularmovies.core.comm_interfaces.CoreToAdapter_Interface;

/**
 * Created by JonathanOliveira on 06/03/17.
 */

public interface MovieGrid_Presenter_Interface extends CoreToAdapter_Interface {

    boolean handleMenuItemClick(MenuItem item);

    void askCoreForData();

    void onAdapterClickCallback(int position);

    Intent createIntent(Context context, Movie extraObject);

    Context getContext();

}
