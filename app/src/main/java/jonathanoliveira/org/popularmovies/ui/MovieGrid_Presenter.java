package jonathanoliveira.org.popularmovies.ui;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import jonathanoliveira.org.popularmovies.Movie;
import jonathanoliveira.org.popularmovies.NetworkUtils;
import jonathanoliveira.org.popularmovies.R;
import jonathanoliveira.org.popularmovies.comm_interfaces.MovieGrid_Activity_Interface;
import jonathanoliveira.org.popularmovies.comm_interfaces.MovieGrid_Presenter_Interface;
import jonathanoliveira.org.popularmovies.ui.helpers.InternetAsyncTask;

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
                loadAsyncTask();
                return true;
        }
        return true;
    }

    @Override
    public void updateAdapter(Movie[] movieArr) {
        activity.getRecyclerViewAdapter().notifyAdapterForDataChange(movieArr);
    }

    @Override
    public void onAdapterClickCallback(int position) {
        Intent intent = createIntent(activity.getContext(), activity.getRecyclerViewAdapter().getAdapterDataSingleItem(position));
        activity.startNewActivityWithIntent(intent);
    }

    @Override
    public Intent createIntent(Context context, Movie extraObject) {
        Intent intent = new Intent(context, MovieDetails_Activity.class);
        intent.putExtra(Intent.EXTRA_TEXT, extraObject);
        return intent;
    }

    @Override
    public void writeToast(String toastText) {
        Toast.makeText(activity.getContext(), toastText, Toast.LENGTH_LONG).show();
    }

    @Override
    public void loadAsyncTask() {
        new InternetAsyncTask(this).execute(NetworkUtils.build_MD_API_Url(activity.getObjectStateBoolean()));
    }

}
