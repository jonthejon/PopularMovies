package jonathanoliveira.org.popularmovies.ui.presenters;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import jonathanoliveira.org.popularmovies.core.beans.Movie;
import jonathanoliveira.org.popularmovies.R;
import jonathanoliveira.org.popularmovies.core.Core;
import jonathanoliveira.org.popularmovies.core.Core_Interface;
import jonathanoliveira.org.popularmovies.ui.views.MovieDetails_Activity;
import jonathanoliveira.org.popularmovies.ui.views.MovieGrid_Activity_Interface;

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
                askCoreForData();
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
    public void writeToast() {
        String toastText = "Something went terribly wrong! Please check your internet connection and try again.";
        Toast.makeText(activity.getContext(), toastText, Toast.LENGTH_LONG).show();
    }

    @Override
    public void askCoreForData() {
        Core_Interface core = Core.getCoreInstance();
        core.registerCoreToAdapterInterface(this);
        core.setBooleanOption(activity.getObjectStateBoolean());
        core.getData(core.getManagerAPICallName());
    }

}
