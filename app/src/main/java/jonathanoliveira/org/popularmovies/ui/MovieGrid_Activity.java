package jonathanoliveira.org.popularmovies.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import jonathanoliveira.org.popularmovies.R;
import jonathanoliveira.org.popularmovies.comm_interfaces.MovieGrid_Activity_Interface;
import jonathanoliveira.org.popularmovies.comm_interfaces.MovieGrid_Presenter_Interface;
import jonathanoliveira.org.popularmovies.ui.helpers.GridAdapter;

public class MovieGrid_Activity extends AppCompatActivity implements MovieGrid_Activity_Interface {

    private RecyclerView mRecyclerView;
    // REFERENCES TO ADAPTER MUST BE OF GRIDADAPTER TYPE INSTEAD OF INTERFACE TYPE IN ORDER FOR ADAPTER TO PROPERLY WORK
    private GridAdapter mGridAdapter;
    private boolean sortByPopularity = true;
    private MovieGrid_Presenter_Interface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MovieGrid_Presenter(this);
        presenter.loadAsyncTask();
        wireViews();
        bindAdapter(setAdapter(setLayoutManager()));
    }

    @Override
    public RecyclerView.Adapter setAdapter(boolean hasLayoutManager) {
        return mGridAdapter = new GridAdapter(presenter, this);
    }

    @Override
    public void bindAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(mGridAdapter);
    }

    @Override
    public boolean setLayoutManager() {
        RecyclerView.LayoutManager gridManager;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridManager = new GridLayoutManager(getApplicationContext(), 2);
        } else {
            gridManager = new GridLayoutManager(getApplicationContext(), 4);
        }
        mRecyclerView.setLayoutManager(gridManager);
        mRecyclerView.setHasFixedSize(true);
        return true;
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
}