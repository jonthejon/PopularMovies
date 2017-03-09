package jonathanoliveira.org.popularmovies.ui.views;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import jonathanoliveira.org.popularmovies.ui.presenters.MovieGrid_Presenter_Interface;
import jonathanoliveira.org.popularmovies.ui.helpers.GridAdapter;

/**
 * Created by JonathanOliveira on 06/03/17.
 */

public interface MovieGrid_Activity_Interface {

    void inflateMenu(Menu menu);

    void setObjectStateBoolean(boolean bool);

    boolean getObjectStateBoolean();

    MovieGrid_Presenter_Interface getPresenter();

    GridAdapter getRecyclerViewAdapter();

    Context getContext();

    void wireViews();

    RecyclerView.Adapter setAdapter();

    void bindAdapter(RecyclerView.Adapter adapter);

    void setLayoutManager();

    void startNewActivityWithIntent(Intent intent);

}
