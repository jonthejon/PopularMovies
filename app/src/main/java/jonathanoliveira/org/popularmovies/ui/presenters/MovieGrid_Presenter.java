package jonathanoliveira.org.popularmovies.ui.presenters;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.view.MenuItem;

import jonathanoliveira.org.popularmovies.R;
import jonathanoliveira.org.popularmovies.core.Core;
import jonathanoliveira.org.popularmovies.core.Core_Interface;
import jonathanoliveira.org.popularmovies.core.beans.Movie;
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
            case R.id.most_popular_menu_item:
                activity.setOptionSelected("most_popular");
/*                if (activity.getObjectStateBoolean()) {
                    return true;
                } else {
                    activity.setObjectStateBoolean(true);
                }*/
                break;
            case R.id.top_rated_menu_item:
                activity.setOptionSelected("top_rated");
/*                if (!activity.getObjectStateBoolean()) {
                    activity.setObjectStateBoolean(false);
                } else {
                    activity.setObjectStateBoolean(false);
                }*/
                break;
            case R.id.favorites_menu_item:
                activity.setOptionSelected("favorites");
                break;
        }
        askCoreForData();
        return true;
    }

    @Override
    public void updateAdapter(Movie[] movieArr) {
        activity.getRecyclerViewAdapter().notifyAdapterForDataChange(movieArr);
    }

        @Override
    public void onAdapterClickCallback(int position) {
        Intent intent = createIntent(activity.getContext(),activity.getRecyclerViewAdapter().getAdapterDataSingleItem(position));
        activity.startNewActivityWithIntent(intent);
    }

    @Override
    public Intent createIntent(Context context, Movie extraObject) {
            Intent intent = new Intent(context, MovieDetails_Activity.class);
            intent.putExtra(Intent.EXTRA_TEMPLATE, extraObject);
//        intent.put`
        return intent;
    }

    @Override
    public void writeError() {
        Snackbar.make(activity.getBaseView(), R.string.snackbar_text, Snackbar.LENGTH_LONG).show();
//        String toastText = "Something went terribly wrong! Please check your internet connection and try again.";
//        Toast.makeText(activity.getContext(), toastText, Toast.LENGTH_LONG).show();
    }

    @Override
    public void askCoreForData() {
//        Log.d("JONATHAN", "askCoreForData: " + activity.getOptionSelected());
        Core_Interface core = Core.getCoreInstance();
        core.registerCoreToAdapterInterface(this);
        int channel = 0;
        switch (activity.getOptionSelected()) {
            case "most_popular":
                channel = core.getAdapterChannel();
                break;

            case "top_rated":
                channel = core.getAdapterChannel();
                break;

            case "favorites":
                channel = core.getCpAdapterChannel();
                break;
        }
//        core.setBooleanOption(activity.getObjectStateBoolean());
//        core.getData(core.getManagerAPICallName());
//        Log.d("JONATHAN", "askCoreForData: " + channel);
        core.getData(channel);
    }

    @Override
    public LoaderManager getLoaderManager() {
        return activity.getActivityLoaderManager();
    }

    @Override
    public Context getContext() {
        return activity.getContext();
    }

    @Override
    public boolean getBooleanOption() {
        return activity.getObjectStateBoolean();
    }

    @Override
    public String getSearchOption() {
        return activity.getOptionSelected();
    }

    @Override
    public void updateRecyclerViewState() {
        activity.updateRecyclerViewState();
    }
}
