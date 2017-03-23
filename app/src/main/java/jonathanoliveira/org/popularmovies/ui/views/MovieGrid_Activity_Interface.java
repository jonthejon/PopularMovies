package jonathanoliveira.org.popularmovies.ui.views;

import android.support.v4.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;

import jonathanoliveira.org.popularmovies.ui.helpers.GridAdapter;
import jonathanoliveira.org.popularmovies.ui.presenters.MovieGrid_Presenter_Interface;

/**
 * Created by JonathanOliveira on 06/03/17.
 */

public interface MovieGrid_Activity_Interface {

    void inflateMenu(Menu menu);

    void setObjectStateBoolean(boolean bool);

    boolean getObjectStateBoolean();

    void setOptionSelected(String selection);
    String getOptionSelected();

    MovieGrid_Presenter_Interface getPresenter();

    GridAdapter getRecyclerViewAdapter();

    Context getContext();

    void wireViews();

    RecyclerView.Adapter setAdapter();

    void bindAdapter(RecyclerView.Adapter adapter);

    void setLayoutManager();

    void startNewActivityWithIntent(Intent intent);

    View getBaseView();

    LoaderManager getActivityLoaderManager();

    void updateRecyclerViewState();
}
