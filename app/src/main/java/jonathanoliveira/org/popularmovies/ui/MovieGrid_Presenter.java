package jonathanoliveira.org.popularmovies.ui;

import android.view.MenuItem;
import android.widget.Toast;

import jonathanoliveira.org.popularmovies.Movie;
import jonathanoliveira.org.popularmovies.R;
import jonathanoliveira.org.popularmovies.comm_interfaces.MovieGrid_Activity_Interface;
import jonathanoliveira.org.popularmovies.comm_interfaces.MovieGrid_Presenter_Interface;

/**
 * Created by JonathanOliveira on 06/03/17.
 */

public class MovieGrid_Presenter implements MovieGrid_Presenter_Interface {

    private MovieGrid_Activity_Interface activity;

    public MovieGrid_Presenter(MovieGrid_Activity_Interface activity) {
        this.activity = activity;
    }

    @Override
    public boolean handleMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.settings_button:
                if (activity.getObjectStateBoolean()) {
                    item.setTitle(R.string.popular);
                    activity.setObjectStateBoolean(false);
                } else {
                    item.setTitle(R.string.rated);
                    activity.setObjectStateBoolean(true);
                }
                activity.loadAsyncTask();
                return true;
        }
        return true;
    }

    @Override
    public void updateAdapter(Movie[] movieArr) {
        activity.getRecyclerViewAdapter().setMoviesArr(movieArr);
    }

    @Override
    public void writeToast(String toastText) {
        Toast.makeText(activity.getContext(), toastText, Toast.LENGTH_LONG).show();
    }
}
