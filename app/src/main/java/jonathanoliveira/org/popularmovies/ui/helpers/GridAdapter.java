package jonathanoliveira.org.popularmovies.ui.helpers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jonathanoliveira.org.popularmovies.R;
import jonathanoliveira.org.popularmovies.core.beans.Movie;
import jonathanoliveira.org.popularmovies.ui.presenters.MovieGrid_Presenter_Interface;

/**
 * Created by JonathanOliveira on 12/01/17.
 */

// REFERENCES TO ADAPTER MUST BE OF GRIDADAPTER TYPE INSTEAD OF INTERFACE TYPE IN ORDER FOR ADAPTER TO PROPERLY WORK
public class GridAdapter extends RecyclerView.Adapter<PosterViewHolder> implements GridAdapter_Interface {

    private Movie[] moviesArr;
    final private MovieGrid_Presenter_Interface presenter;


    public GridAdapter(MovieGrid_Presenter_Interface presenter) {
        this.presenter = presenter;
    }

    @Override
    public void notifyAdapterForDataChange(Movie[] moviesArr) {
        this.moviesArr = null;
        this.moviesArr = moviesArr;
        notifyDataSetChanged();
    }

    @Override
    public Movie getAdapterDataSingleItem(int position) {
        return moviesArr[position];
    }

    @Override
    public PosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.poster_view_layout;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem,parent,false);
        return new PosterViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(PosterViewHolder holder, int position) {
        String movieName = moviesArr[position].getMovie_title();
        holder.bindData(movieName, moviesArr[position].getPoster_path());
    }

    @Override
    public int getItemCount() {
        if (moviesArr == null) {
            return 0;
        }
        return moviesArr.length;
    }

    @Override
    public void onAdapterClickCallback(int position) {
        presenter.onAdapterClickCallback(position);
    }

    @Override
    public Context getContext() {
        return presenter.getContext();
    }

}
