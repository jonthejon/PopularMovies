package jonathanoliveira.org.popularmovies.ui.views;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import jonathanoliveira.org.popularmovies.R;
import jonathanoliveira.org.popularmovies.ui.helpers.GridAdapter;
import jonathanoliveira.org.popularmovies.ui.presenters.MovieGrid_Presenter;
import jonathanoliveira.org.popularmovies.ui.presenters.MovieGrid_Presenter_Interface;

public class MovieGrid_Activity extends AppCompatActivity implements MovieGrid_Activity_Interface {

    private RecyclerView mRecyclerView;
    // REFERENCES TO ADAPTER MUST BE OF GRIDADAPTER TYPE INSTEAD OF INTERFACE TYPE IN ORDER FOR ADAPTER TO PROPERLY WORK
    private GridAdapter mGridAdapter;
    private boolean sortByPopularity = true;
    private MovieGrid_Presenter_Interface presenter;
    private final String OPTION_VALUE = "search_option";
    private final String ADAPTER_VALUE = "adapter_value";

    private final String POPULAR_OPTION = "most_popular";
    private final String RATED_OPTION = "top_rated";
    private final String FAVORITE_OPTION = "favorites";
    private final String NEW_OPTION_VALUE = "search_option";
    private String optionSelection = POPULAR_OPTION;

    private Parcelable mRVScrollPos = null;
    RecyclerView.LayoutManager gridManager;
    private final String RV_STATE_KEY = "recycler_state";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MovieGrid_Presenter(this);
        wireViews();
        setLayoutManager();
        bindAdapter(setAdapter());
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(OPTION_VALUE)) {
                boolean savedOption = savedInstanceState.getBoolean(OPTION_VALUE);
                setObjectStateBoolean(savedOption);
            }
            if (savedInstanceState.containsKey(NEW_OPTION_VALUE)) {
                String optionSelected = savedInstanceState.getString(NEW_OPTION_VALUE);
                setOptionSelected(optionSelected);
            }
            if (savedInstanceState.containsKey(RV_STATE_KEY)) {
                mRVScrollPos = savedInstanceState.getParcelable(RV_STATE_KEY);
            }
        }
        presenter.askCoreForData();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (getOptionSelected().equals(FAVORITE_OPTION)) {
            presenter.askCoreForData();
        }
    }

    @Override
    public GridAdapter setAdapter() {
        return mGridAdapter = new GridAdapter(presenter);
    }

    @Override
    public void bindAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(mGridAdapter);
    }

    @Override
    public void setLayoutManager() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridManager = new GridLayoutManager(getApplicationContext(), 2);
        } else {
            gridManager = new GridLayoutManager(getApplicationContext(), 4);
        }
        mRecyclerView.setLayoutManager(gridManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void wireViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_grid);
    }

    @Override
    public void startNewActivityWithIntent(Intent intent) {
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        inflateMenu(menu);
        return true;
    }

    @Override
    public void inflateMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return presenter.handleMenuItemClick(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void setObjectStateBoolean(boolean bool) {
        this.sortByPopularity = bool;
    }

    @Override
    public boolean getObjectStateBoolean() {
        return this.sortByPopularity;
    }

    @Override
    public void setOptionSelected(String selection) {
        switch (selection) {
            case POPULAR_OPTION:
                this.optionSelection = POPULAR_OPTION;
                break;

            case RATED_OPTION:
                this.optionSelection = RATED_OPTION;
                break;

            case FAVORITE_OPTION:
                this.optionSelection = FAVORITE_OPTION;
                break;
        }
    }

    @Override
    public String getOptionSelected() {
        return this.optionSelection;
    }

    @Override
    public MovieGrid_Presenter_Interface getPresenter() {
        return this.presenter;
    }

    @Override
    public GridAdapter getRecyclerViewAdapter() {
        return mGridAdapter;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public View getBaseView() {
        return findViewById(R.id.activity_main);
    }


    @Override
    public LoaderManager getActivityLoaderManager() {
        return getSupportLoaderManager();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(OPTION_VALUE, getObjectStateBoolean());
        outState.putString(NEW_OPTION_VALUE, getOptionSelected());
        mRVScrollPos = mRecyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(RV_STATE_KEY,mRVScrollPos);

//        mRVScrollPos = gridManager.onSaveInstanceState();
//        outState.putParcelable(RV_STATE_KEY, mRVScrollPos);

//        mRecyclerView.getLayoutManager().pos
//        mRVScrollPos = mRecyclerView.getLayoutManager().onSaveInstanceState();
//        mRVScrollPos = gridManager.onSaveInstanceState();
//        outState.putParcelable(RV_STATE_KEY,mRVScrollPos);
    }

    @Override
    public void updateRecyclerViewState() {
        if (mRVScrollPos != null) {
            mRecyclerView.getLayoutManager().onRestoreInstanceState(mRVScrollPos);
        }
    }
}