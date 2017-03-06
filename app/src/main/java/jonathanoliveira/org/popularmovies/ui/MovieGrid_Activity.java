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

import com.squareup.picasso.Picasso;

import jonathanoliveira.org.popularmovies.Movie;
import jonathanoliveira.org.popularmovies.NetworkUtils;
import jonathanoliveira.org.popularmovies.R;
import jonathanoliveira.org.popularmovies.comm_interfaces.MovieGrid_Activity_Interface;
import jonathanoliveira.org.popularmovies.comm_interfaces.MovieGrid_Presenter_Interface;
import jonathanoliveira.org.popularmovies.ui.helpers.GridAdapter;
import jonathanoliveira.org.popularmovies.ui.helpers.InternetAsyncTask;

public class MovieGrid_Activity extends AppCompatActivity implements GridAdapter.GridItemClickListener, MovieGrid_Activity_Interface {

    private RecyclerView mRecyclerView;
    private GridAdapter mGridAdapter;
    private Picasso picasso;
    private boolean sortByPopularity = true;
    private MovieGrid_Presenter_Interface presenter;
    // completed: 16/01/17 implement the boolean that will inform which type of sort will be displayed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MovieGrid_Presenter(this);
        loadAsyncTask();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_grid);
        RecyclerView.LayoutManager gridManager;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridManager = new GridLayoutManager(getApplicationContext(), 2);
        } else {
            gridManager = new GridLayoutManager(getApplicationContext(), 4);
        }
        mRecyclerView.setLayoutManager(gridManager);
        mRecyclerView.setHasFixedSize(true);
        Picasso.Builder picassoBuilder = new Picasso.Builder(this);
        this.picasso = picassoBuilder.build();
        mGridAdapter = new GridAdapter(this, this.picasso);
        mRecyclerView.setAdapter(mGridAdapter);
    }

    @Override
    public void loadAsyncTask() {
        loadMoviesData();
    }

    public void loadMoviesData() {
        new InternetAsyncTask(getPresenter()).execute(NetworkUtils.build_MD_API_Url(this.sortByPopularity));
    }

    @Override
    public void OnClickView(int position) {
        Movie movie = mGridAdapter.getMovie(position);
        Intent intent = new Intent(this, MovieDetails_Activity.class);
        intent.putExtra(Intent.EXTRA_TEXT, movie);
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